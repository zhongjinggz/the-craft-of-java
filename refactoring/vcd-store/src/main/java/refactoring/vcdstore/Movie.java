package refactoring.vcdstore;

public class Movie {

    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;

    private final String title;
    private final PriceType priceType;

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

    double calAmount(int daysRented) {
        double thisAmount = 0;

        // 取得影片出租价格
        switch (priceType.getCode()) {
            // 普通片
            case REGULAR:
                thisAmount += 2;
                if (daysRented > 2)
                    thisAmount += (daysRented - 2) * 1.5;
                break;
            // 新片
            case NEW_RELEASE:
                thisAmount += daysRented * 3;
                break;
            // 儿童片
            case CHILDREN:
                thisAmount += 1.5;
                if (daysRented > 3)
                    thisAmount += (daysRented - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
