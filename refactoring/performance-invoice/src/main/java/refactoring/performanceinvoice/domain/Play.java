package refactoring.performanceinvoice.domain;

public class Play {
    private String id;
    private String name;

    public PlayType playType;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.setTypeName(typeName);
        this.playType = new PlayType(typeName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return playType.getName();
    }

    public void setTypeName(String typeName) {
    }
}
