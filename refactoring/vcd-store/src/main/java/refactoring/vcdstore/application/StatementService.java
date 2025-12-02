package refactoring.vcdstore.application;

import refactoring.vcdstore.domain.Customer;
import refactoring.vcdstore.domain.Rental;

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
//DONE 过长函数
//DONE 特性依恋
//DONE 基本类型偏执
//DONE 重复 Switch
//DONE 重构到分层架构
//DONE 去除魔法数字
//DONE 避免 String +=
//TODO 隐藏PriceType中的常量
public class StatementService {
    public String printStatement(Customer customer) {
        customer.calRentals();
        return buildStatement(customer);
    }

    private String buildStatement(Customer customer) {
        //表头
        StringBuilder builder = new StringBuilder("Rental Record for ")
            .append(customer.getName())
            .append("\n");

        // 租借记录
        for (Rental rental : customer.getRentals()) {
            builder.append("\t").append(rental.getMovie().getTitle())
                .append("\t").append(rental.getAmount())
                .append("\n");
        }

        // 表尾
        builder.append("Amount owed is ").append(customer.getAmount()).append("\n")
            .append("You earned ").append(customer.getFrequentPoints()).append(" frequent renter points");

        return builder.toString();
    }
}
