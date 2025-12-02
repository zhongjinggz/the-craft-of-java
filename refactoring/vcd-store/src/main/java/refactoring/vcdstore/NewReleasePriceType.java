package refactoring.vcdstore;

public class NewReleasePriceType extends PriceType {

    public NewReleasePriceType(int code) {
        super(code);
    }

    @Override
    double calAmount(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int calFrequentPoints(int daysRented) {
        int thisPoints = 1;

        if ( daysRented > 1) {
            thisPoints += 1;
        }
        return thisPoints;
    }
}
