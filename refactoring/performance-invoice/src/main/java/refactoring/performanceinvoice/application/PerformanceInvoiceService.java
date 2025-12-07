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
        initPlays();

        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomerName());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = findPlayById(perf.getPlayId());

            invoice.addItem(play
                , play.calAmount(perf)
                , play.calPoints(perf)
                , perf.getAudience());

        }


        repository.save(invoice);
        return invoice;
    }

    private Play findPlayById(String playId) {
        return plays.get(playId);
    }

    private void initPlays() {
        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

}
