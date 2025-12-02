package refactoring.vcdstore;

/**
 * 影片出租价格计算方式：
 * <p>
 * REGULAR：起步价2元，租超过2天后，每天1.5元
 * NEW_RELEASE：每天3元，不设起步价
 * CHILDREN：起步价1.5元，租超过3天后，每天1.5元
 * <p>
 * <p>
 * 常客积分计算方式：
 * <p>
 * 每租1张影碟得1积分，
 * 如果是新片而且租超过1天的话，再加1积分
 */

//DONE 可变数据
//DONE 优化命名
//DONE 缩短循环
//DONE Rental 自己记录金额
//DONE 复杂循环
//DONE Customer 自己记录总金额和总点数
//TODO 过长函数
//TODO 重复 Switch
//TODO 基本类型偏执
public class StatementService {

    public String printStatement(Customer customer) {

        double totalAmount = 0; // 总消费金。
        int totalPoints = 0; // 常客积点

        String result = "Rental Record for " + customer.getName() + "\n";

        for (Rental rental : customer.getRentals()) {

            double thisAmount = calThisAmount(rental);
            totalAmount += thisAmount;

            rental.setAmount(thisAmount);

            int thisPoints = calThisPoints(rental);
            totalPoints += thisPoints;
        }

        customer.setAmount(totalAmount);
        customer.setFrequentPoints(totalPoints);

        // 显示租借记录
        for (Rental rental : customer.getRentals()) {
            result += "\t" + rental.getMovie().getTitle() + "\t"
                + rental.getAmount() + "\n";

        }
        // add footer lines（结尾打印）
        result += "Amount owed is " + customer.getAmount() + "\n";
        result += "You earned " + customer.getFrequentPoints()
            + " frequent renter points";

        return result;
    }

    // 计算常客积点
    private int calThisPoints(Rental rental) {
        int thisPoints = 1;

        // add bonus for a two days new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
            rental.getDaysRented() > 1) {
            thisPoints++;
        }
        return thisPoints;
    }

    private double calThisAmount(Rental rental) {
        double thisAmount = 0;

        // 取得影片出租价格
        switch (rental.getMovie().getPriceCode()) {
            // 普通片
            case Movie.REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                break;
            // 新片
            case Movie.NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;
            // 儿童片
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                    thisAmount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
