package carpenter.model;

public class ItemResponse {
    private Item item;
    private String response;

    public ItemResponse(Item item, String response) {
        this.item = item;
        this.response = response;
    }

    public Item getItem() {
        return item;
    }

    public String getResponse() {
        return response;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setResponse(String response) {
        this.response = response;
    }

	public boolean isNotId() {
		return this.item.getId()==null;
	}

	public String getSuggestedId() {
		return item.getSuggestedId();
	}
}
