package refactoring.vcdstore;

public class Movie {

    private final String title;
    final PriceType priceType;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceType = buildPriceType(priceCode);
    }

    private static PriceType buildPriceType(int priceCode) {
        switch (priceCode) {
            case PriceType.NEW_RELEASE:
                return new NewReleasePriceType(priceCode);
            case PriceType.CHILDREN:
                return new ChildrenPriceType(priceCode);
            case PriceType.REGULAR:
                return new RegularPriceType(priceCode);
            default:
                throw new IllegalArgumentException("Invalid Price Code");
        }
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

    int calFrequentPoints(int daysRented) {
        return priceType.calFrequentPoints(daysRented);
    }

    double calAmount(int daysRented) {
        return priceType.calAmount(daysRented);
    }
}
