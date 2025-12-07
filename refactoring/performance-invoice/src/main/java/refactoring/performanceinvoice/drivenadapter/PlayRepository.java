package refactoring.performanceinvoice.drivenadapter;

import refactoring.performanceinvoice.domain.Play;

import java.util.HashMap;
import java.util.Map;

public class PlayRepository {
    Map<String, Play> plays = new HashMap<>();

    public void initPlays() {
        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

    public Play findPlayById(String playId) {
        return plays.get(playId);
    }
}
