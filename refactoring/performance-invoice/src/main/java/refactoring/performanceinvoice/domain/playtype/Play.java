package refactoring.performanceinvoice.domain.playtype;

import refactoring.performanceinvoice.application.Performance;

//TODO 用 Factory 代替 valueOf
public class Play {
    private String id;
    private String name;
    private PlayType type;

    public Play(String id, String name, String typeString) {
        this.id = id;
        this.name = name;
        this.type = PlayType.valueOf(typeString);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int calAmount(int audienceCount) {
        return type.calAmount(audienceCount);
    }

    public int calAudiencePoints(Performance perf) {
        return type.calAudiencePoints(perf.getAudienceCount());
    }

    public PlayType getType() {
        return type;
    }
}
