package refactoring.performanceinvoice.drivenadapter;

import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.domain.playtype.Play;
import refactoring.performanceinvoice.domain.playtype.PlayTypeRepository;

import java.util.HashMap;
import java.util.Map;

public class PlayTypeRepositoryMem implements PlayTypeRepository {
    private final Map<String, Play> plays = new HashMap<>();

    public PlayTypeRepositoryMem() {
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

    @Override
    public Play findById(String playId, PerformanceInvoiceService performanceInvoiceService) {
        return plays.get(playId);
    }
}
