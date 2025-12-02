package refactoring.vcdstore.domain.pricetype;

public class NewReleasePriceType extends PriceType {

    public static final int CODE = 1;

    private static final int UNIT_PRICE = 3;
    private static final int BASE_PRICE = 1;
    private static final int POINTS_THRESHOLD = 1;
    private static final int EXTRA_POINTS = 1;

    public NewReleasePriceType() {
        super(CODE);
    }

    @Override
    public double calAmount(int daysRented) {
        return daysRented * UNIT_PRICE;
    }

    @Override
    public int calFrequentPoints(int daysRented) {
        int points = BASE_PRICE;

        if ( daysRented > POINTS_THRESHOLD) {
            points += EXTRA_POINTS;
        }
        return points;
    }
}
