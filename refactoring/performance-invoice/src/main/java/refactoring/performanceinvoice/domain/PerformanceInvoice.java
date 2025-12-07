package refactoring.performanceinvoice.domain;

import java.util.ArrayList;
import java.util.List;

public class PerformanceInvoice {
    private Long id;

    private String customer;

    private int amount;

    private int audiencePoints;

    private List<PerformanceInvoiceItem> items = new ArrayList<>();

    public PerformanceInvoice(String customer) {
        this.customer = customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void addItem(PerformanceInvoiceItem item) {
        this.items.add(item);
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceInvoiceItem> getItems() {
        return items;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setVolumePoints(int volumeCredits) {
        this.audiencePoints = volumeCredits;
    }

    public int getAmount() {
        return amount;
    }

    public int getAudiencePoints() {
        return audiencePoints;
    }

    public int addItem2(int itemAmount, Play play, int audienceCount) {
        this.items.add(new PerformanceInvoiceItem(play.getName()
            , itemAmount, audienceCount));
        amount += itemAmount;
        return amount;
    }
}
