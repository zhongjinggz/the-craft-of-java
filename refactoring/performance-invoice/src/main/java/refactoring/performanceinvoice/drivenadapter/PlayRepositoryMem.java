package refactoring.performanceinvoice.drivenadapter;

import org.springframework.stereotype.Repository;
import refactoring.performanceinvoice.domain.play.Play;
import refactoring.performanceinvoice.domain.play.PlayRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayRepositoryMem implements PlayRepository {
    private final Map<String, Play> plays = new HashMap<>();

    public PlayRepositoryMem() {
        initPlays();
    }

    public void initPlays() {
        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

    @Override
    public Play findPlayById(String playId) {
        return plays.get(playId);
    }
}
