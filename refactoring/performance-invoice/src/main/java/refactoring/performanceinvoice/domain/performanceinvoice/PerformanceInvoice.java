package refactoring.performanceinvoice.domain.performanceinvoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceInvoice {
    private Long id;

    private String customer;

    private int amount;

    private int audiencePoints;

    private final List<PerformanceInvoiceItem> items = new ArrayList<>();

    public PerformanceInvoice(String customer) {
        this.customer = customer;
    }

    public void addItem(String name, int itemAmount, int itemPoints, int audienceCount) {
        this.items.add(new PerformanceInvoiceItem(name, itemAmount, audienceCount));
        this.amount += itemAmount;
        this.audiencePoints += itemPoints;
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceInvoiceItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAudiencePoints(int audiencePoints) {
        this.audiencePoints = audiencePoints;
    }

    public int getAmount() {
        return amount;
    }

    public int getAudiencePoints() {
        return audiencePoints;
    }
}
