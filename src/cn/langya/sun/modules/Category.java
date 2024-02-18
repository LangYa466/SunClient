package cn.langya.sun.modules;

public enum Category {
    Combat("Combat"),
    Move("Move"),
    World("World"),
    Render("Render"),
    Client("Client"),
    Misc("Misc"),
    Player("Player");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
