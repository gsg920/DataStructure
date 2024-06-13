package projects;
public class Review {
    private String userId;
    private String comment;
    private double rating;

    public Review(String userId, String comment, double rating) {
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public double getRating() {
        return rating;
    }
}
