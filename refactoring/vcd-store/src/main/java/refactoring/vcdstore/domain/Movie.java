package refactoring.vcdstore.domain;

import refactoring.vcdstore.domain.pricetype.ChildrenPriceType;
import refactoring.vcdstore.domain.pricetype.NewReleasePriceType;
import refactoring.vcdstore.domain.pricetype.PriceType;
import refactoring.vcdstore.domain.pricetype.RegularPriceType;

public class Movie {

    private final String title;
    final PriceType priceType;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceType = buildPriceType(priceCode);
    }

    private static PriceType buildPriceType(int priceCode) {
        return switch (priceCode) {
            case NewReleasePriceType.CODE -> new NewReleasePriceType();
            case ChildrenPriceType.CODE -> new ChildrenPriceType();
            case RegularPriceType.CODE -> new RegularPriceType();
            default -> throw new IllegalArgumentException("Invalid Price Code");
        };
    }

    public String getTitle() {
        return title;
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
