package refactoring.vcdstore.domain.pricetype;

public class NewReleasePriceType extends PriceType {

    public NewReleasePriceType(int code) {
        super(code);
    }

    @Override
    public double calAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int calFrequentPoints(int daysRented) {
        int thisPoints = 1;

        if ( daysRented > 1) {
            thisPoints += 1;
        }
        return thisPoints;
    }
}
