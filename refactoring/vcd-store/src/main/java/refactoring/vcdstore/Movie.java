package refactoring.vcdstore;

public class Movie {

    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private final String title;
    final PriceType priceType;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceType = new PriceType(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return priceType.getCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
            "title='" + title + '\'' +
            ", priceCode=" + priceType.getCode() +
            '}';
    }

}
