package tdd.performancebill;

public class PerformanceBillItem {

    private Long id;

    private String name;

    private int amount;

    private int audience;

    public PerformanceBillItem(String name, int amount, int audience) {
        this.name = name;
        this.amount = amount;
        this.audience = audience;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getAudience() {
        return audience;
    }
}
