package refactoring.performanceinvoice.domain;

//TODO 避免对 Performance 的依赖
public class Play {
    String id;
    String name;
    String typeString;
    public PlayType type;

    public Play(String id, String name, String typeString) {
        this.id = id;
        this.name = name;
        this.typeString = typeString;
        this.type = PlayType.valueOf(typeString);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeString() {
        return typeString;
    }

    public int calAmount(int audienceCount) {
        return type.calAmount(audienceCount);
    }
}
