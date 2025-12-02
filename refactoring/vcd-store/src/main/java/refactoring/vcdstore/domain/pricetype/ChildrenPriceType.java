package refactoring.vcdstore.domain.pricetype;

public class ChildrenPriceType extends PriceType {

    public ChildrenPriceType(int code) {
        super(code);
    }

    @Override
    public double calAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3) {
            amount += (daysRented - 3) * 1.5;
        }
        return amount;
    }

    @Override
    public int calFrequentPoints(int daysRented) {
        return 1;
    }
}
