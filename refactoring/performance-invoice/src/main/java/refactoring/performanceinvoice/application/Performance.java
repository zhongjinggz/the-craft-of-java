package refactoring.performanceinvoice.application;

public class Performance {

    private final String playId;
    private final int audienceCount;

    public Performance(String playId, int audienceCount) {
        this.playId = playId;
        this.audienceCount = audienceCount;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudienceCount() {
        return audienceCount;
    }
}
