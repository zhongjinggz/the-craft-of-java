package refactoring.vcdstore;

public class PriceType extends AbstractPriceType {

    public PriceType(int code) {
        super(code);
    }

    @Override
    double calAmount(int daysRented) {
        double thisAmount = 0;

        // 取得影片出租价格
        switch (getCode()) {
            // 普通片
            case Movie.REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            // 新片
            case Movie.NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            // 儿童片
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

    @Override
    int calFrequentPoints(int daysRented) {
        int thisPoints = 1;

        if ((getCode() == Movie.NEW_RELEASE) &&
            daysRented > 1) {
            thisPoints++;
        }
        return thisPoints;
    }
}
