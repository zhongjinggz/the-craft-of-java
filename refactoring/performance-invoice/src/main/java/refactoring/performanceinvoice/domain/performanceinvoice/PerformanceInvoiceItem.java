package refactoring.performanceinvoice.domain.performanceinvoice;

public class PerformanceInvoiceItem {

    private Long id;

    private final String name;

    private final int amount;

    private final int audienceCount;

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
