public class Film {
    private String name;
    private int lengthMins;
    private int rating = -1;
    private String comments;

    public Film(String name, int lengthMins) {
        this.name = name;
        this.lengthMins = lengthMins;
        this.comments = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthMins() {
        return lengthMins;
    }

    public void setLengthMins(int lengthMins) {
        this.lengthMins = lengthMins;
    }

    public String getRating() {
        return getRatingStars();
    }

    public int getRatingValue() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    private String getRatingStars() {
        String output = "";
        if (this.rating > 0) {
            for (int i = 0; i < this.rating; i++) output += "*";
        }
        return output;
    }

    @Override
    public String toString() {
        String stars = getRatingStars();
        return "Name: " + this.name + " Length (mins): " + this.lengthMins + " Rating: " + stars;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
