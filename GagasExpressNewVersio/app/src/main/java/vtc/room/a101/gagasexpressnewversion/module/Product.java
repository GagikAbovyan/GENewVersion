package vtc.room.a101.gagasexpressnewversion.module;


public class Product {
    private final String title;
    private final String description;
    private final String category;
    private final int price;
    private int[] imageArrayId = new int[3];
    private String allDescription;
    private boolean isFavorite;
    private boolean isStore;
    private float star;
    private String videoUrl;


    public Product(final String title, final String description, final String category, final int price, final int[] imageArrayId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imageArrayId = imageArrayId;
    }

    public void setAllDescription(String allDescription) {
        this.allDescription = allDescription;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int[] getImageArrayId() {
        return imageArrayId;
    }

    public String getAllDescription() {
        return allDescription;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean isStore() {
        return isStore;
    }

    public float getStar() {
        return star;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
