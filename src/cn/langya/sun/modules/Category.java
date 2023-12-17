package cn.langya.sun.modules;

public enum Category {
    Combat("战斗"),
    Move("移动"),
    Render("视觉"),
    Client("客户端"),
    Misc("其他");

    private String displayName;

    private Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
