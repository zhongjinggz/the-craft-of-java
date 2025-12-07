package refactoring.performanceinvoice.application;

import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.Play;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.drivenadapter.PlayRepository;
import refactoring.performanceinvoice.drivingadapter.Performance;
import refactoring.performanceinvoice.drivingadapter.PerformanceSummary;

import java.util.HashMap;
import java.util.Map;

public class PerformanceInvoiceService {
    PerformanceInvoiceRepository repository;
    PlayRepository playRepository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository repository) {
        this.repository = repository;
        playRepository = new PlayRepository();
    }


    Map<String, Play> plays = new HashMap<>();
    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {
        playRepository.initPlays();

        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomerName());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = playRepository.findPlayById(perf.getPlayId());

            invoice.addItem(play
                , play.calAmount(perf)
                , play.calPoints(perf)
                , perf.getAudience());

        }


        repository.save(invoice);
        return invoice;
    }

}
