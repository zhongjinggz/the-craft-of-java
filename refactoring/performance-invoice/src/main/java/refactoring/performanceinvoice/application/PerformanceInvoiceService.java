package refactoring.performanceinvoice.application;

import org.springframework.stereotype.Service;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.Play;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PerformanceInvoiceService {

    private final PerformanceInvoiceRepository repository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository repository) {
        this.repository = repository;
    }

    Map<String, Play> plays = new HashMap<>();

    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {

        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));

        int totalAmount = 0;
        int totalAudiencePoints = 0;

        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomer());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            int thisAmount;

            if (play.getType().equals("tragedy")) {
                thisAmount = 40000;
                if (perf.getAudience() > 30) {
                    thisAmount += 1000 * (perf.getAudience() - 30);
                }
            } else if (play.getType().equals("comedy")) {
                thisAmount = 30000;
                if (perf.getAudience() > 20) {
                    thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                }
                thisAmount += 300 * perf.getAudience();
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            totalAudiencePoints += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                totalAudiencePoints += Math.floorDiv(perf.getAudience(), 5);
            }

            totalAmount += thisAmount;

            // 添加账单项
            invoice.addItem(play.getName(),thisAmount, perf.getAudience());

        }

        //设置账单金额和积分
        invoice.setTotalAmount(totalAmount);
        invoice.setTotalAudiencePoints(totalAudiencePoints);

        repository.save(invoice);
        return invoice;
    }
}
