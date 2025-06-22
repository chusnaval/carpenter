package carpenter.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import carpenter.model.Item;
import carpenter.model.MovieGenre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:sqlite:items.db";
    private HikariDataSource dataSource;

    public DatabaseConnector() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_URL);
        config.setConnectionTestQuery("SELECT 1");
        config.setMaximumPoolSize(10);
        this.dataSource = new HikariDataSource(config);
        initializeDatabase();
    }

    private void initializeDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS items (" +
                "id TEXT PRIMARY KEY, " +
                "type TEXT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "genre_tag TEXT NOT NULL, " +
                "year TEXT, " +
                "owned BOOLEAN DEFAULT FALSE)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(createTableSQL)) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertItems(List<Item> items) {
        String insertSQL = "INSERT OR REPLACE INTO items (id, type, title, genre_tag, year, owned) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(insertSQL)) {
                for (Item item : items) {
                    stmt.setString(1, item.getId());
                    stmt.setString(2, item.getType());
                    stmt.setString(3, item.getTitle());
                    stmt.setString(4, item.getGenre().getTag());
                    stmt.setString(5, item.getYear());
                    stmt.setBoolean(6, item.isOwned());
                    stmt.addBatch();
                }
                stmt.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Item item) {
        String updateSQL = "UPDATE items SET type = ?, title = ?, genre_tag = ?, year = ?, owned = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(updateSQL)) {
            stmt.setString(1, item.getType());
            stmt.setString(2, item.getTitle());
            stmt.setString(3, item.getGenre().getTag());
            stmt.setString(4, item.getYear());
            stmt.setBoolean(5, item.isOwned());
            stmt.setString(6, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> searchItems(String type, String title, String genreTag, String year, Boolean owned) {
        List<Item> results = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM items WHERE 1=1");

        if (type != null && !type.isEmpty()) {
            queryBuilder.append(" AND type = ?");
        }
        if (title != null && !title.isEmpty()) {
            queryBuilder.append(" AND title LIKE ?");
        }
        if (genreTag != null && !genreTag.isEmpty()) {
            queryBuilder.append(" AND genre_tag = ?");
        }
        if (year != null && !year.isEmpty()) {
            queryBuilder.append(" AND year = ?");
        }
        if (owned != null) {
            queryBuilder.append(" AND owned = ?");
        }

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(queryBuilder.toString())) {

            int index = 1;
            if (type != null && !type.isEmpty()) {
                stmt.setString(index++, type);
            }
            if (title != null && !title.isEmpty()) {
                stmt.setString(index++, "%" + title + "%");
            }
            if (genreTag != null && !genreTag.isEmpty()) {
                stmt.setString(index++, genreTag);
            }
            if (year != null && !year.isEmpty()) {
                stmt.setString(index++, year);
            }
            if (owned != null) {
                stmt.setBoolean(index, owned);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String tag = rs.getString("genre_tag");
                    MovieGenre genre = MovieGenre.fromText(tag);

                    Item item = new Item(
                    		rs.getString("id"),
                            rs.getString("type"),
                            rs.getString("title"),
                            genre,
                            rs.getString("year"),
                            rs.getBoolean("owned")
                    );
                    results.add(item);
                }
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return results;
    }

    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
