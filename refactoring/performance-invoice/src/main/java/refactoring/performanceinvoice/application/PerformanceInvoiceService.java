package refactoring.performanceinvoice.application;

import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.Play;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.drivingadapter.Performance;
import refactoring.performanceinvoice.drivingadapter.PerformanceSummary;

import java.util.HashMap;
import java.util.Map;

public class PerformanceInvoiceService {
    PerformanceInvoiceRepository repository;

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
        int totalPoints = 0;

        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomer());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());

            int thisAmount = calAmount(play, perf.getAudience());
            totalAmount += thisAmount;

            int thisPoints = calPoints(play, perf.getAudience());
            totalPoints += thisPoints;


            // 添加账单项
            invoice.addItem(play.getName(),thisAmount, perf.getAudience());


        }

        //设置账单金额和积分
        invoice.setTotalAmount(totalAmount);
        invoice.setVolumePoints(totalPoints);

        repository.save(invoice);
        return invoice;
    }

    private int calPoints(Play play, int audienceCount) {
        int thisPoints = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(play.getType())) {
            thisPoints += Math.floorDiv(audienceCount, 5);
        }
        return thisPoints;
    }

    private int calAmount(Play play, int audience) {
        int thisAmt;

        if (play.getType().equals("tragedy")) {
            thisAmt = 40000;
            if (audience > 30) {
                thisAmt += 1000 * (audience - 30);
            }
        } else if (play.getType().equals("comedy")) {
            thisAmt = 30000;
            if (audience > 20) {
                thisAmt += 10000 + 500 * (audience - 20);
            }
            thisAmt += 300 * audience;
        } else {
            throw new IllegalArgumentException("戏剧类型不正确!");
        }
        return thisAmt;
    }
}
