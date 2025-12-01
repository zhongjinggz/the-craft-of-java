package refactoring.performanceinvoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PerformanceInvoiceController {

    Map<String, Play> plays = new HashMap<>();

    @Autowired
    PerformanceInvoiceRepository repository;

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createBill(@RequestBody PerformanceSummary performanceSummary) {
        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));

        int totalAmount = 0;
        int volumeCredits = 0;

        PerformanceInvoice bill = new PerformanceInvoice(
                performanceSummary.getCustomer());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            int thisAmt;

            if (play.getType().equals("tragedy")) {
                thisAmt = 40000;
                if (perf.getAudience() > 30) {
                    thisAmt += 1000 * (perf.getAudience() - 30);
                }
            } else if (play.getType().equals("comedy")) {
                thisAmt = 30000;
                if (perf.getAudience() > 20) {
                    thisAmt += 10000 + 500 * (perf.getAudience() - 20);
                }
                thisAmt += 300 * perf.getAudience();
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floorDiv(perf.getAudience(), 5);
            }

            totalAmount += thisAmt;

            // 添加账单项
            bill.addItem(play.getName(),thisAmt, perf.getAudience());


        }

        //设置账单金额和积分
        bill.setTotalAmount(totalAmount);
        bill.setVolumePoints(volumeCredits);

        repository.save(bill);

        return bill;
    }

}
