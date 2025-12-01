package refactoring.performanceinvoice.application;

import org.springframework.stereotype.Service;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.playtype.Play;
import refactoring.performanceinvoice.domain.playtype.PlayTypeRepository;

import java.util.HashMap;
import java.util.Map;

//TODO plays 应放在数据库
//清理其他类

@Service
public class PerformanceInvoiceService {

    private final PerformanceInvoiceRepository performanceInvoiceRepository;
    private final PlayTypeRepository playTypeRepository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository performanceInvoiceRepository
        , PlayTypeRepository playTypeRepository) {

        this.performanceInvoiceRepository = performanceInvoiceRepository;
        this.playTypeRepository = playTypeRepository;
    }

    Map<String, Play> plays = new HashMap<>();

    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {

        initPlays();

        int totalAmount = 0;
        int totalAudiencePoints = 0;

        PerformanceInvoice invoice = new PerformanceInvoice(
            performanceSummary.getCustomer());

        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());

            int amount = play.calAmount(perf.getAudienceCount());
            totalAmount += amount;

            int audiencePoints = play.calAudiencePoints(perf);
            totalAudiencePoints += audiencePoints;

            // 添加账单项
            invoice.addItem(play.getName(), amount, perf.getAudienceCount());

        }

        //设置账单金额和积分
        invoice.setTotalAmount(totalAmount);
        invoice.setTotalAudiencePoints(totalAudiencePoints);

        performanceInvoiceRepository.save(invoice);
        return invoice;
    }

    private void initPlays() {
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

}
