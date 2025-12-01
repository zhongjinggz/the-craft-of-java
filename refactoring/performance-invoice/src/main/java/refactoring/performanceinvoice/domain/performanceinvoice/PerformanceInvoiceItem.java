package refactoring.performanceinvoice.domain.performanceinvoice;

public class PerformanceInvoiceItem {

    private Long id;

    private String name;

    private int amount;

    private int audienceCount;

    public PerformanceInvoiceItem(String name, int amount, int audienceCount) {
        this.name = name;
        this.amount = amount;
        this.audienceCount = audienceCount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getAudienceCount() {
        return audienceCount;
    }
}
