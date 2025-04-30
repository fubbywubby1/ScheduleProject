public enum Label {
    WORK("-fx-background-color:rgb(249, 199, 64);", "Work"),
    SCHOOL("-fx-background-color:rgb(245, 91, 155);", "School"),
    STUDY("-fx-background-color:rgb(200, 200, 200);", "Study"),
    CLUB("-fx-background-color:rgb(180, 180, 255);" , "Club"),
    LEISURE("-fx-background-color:rgb(255, 200, 200);", "Leisure"),
    SELFCARE("-fx-background-color:rgb(154, 187, 246);", "Self Care"),
    CHORES("-fx-background-color:rgb(200, 200, 200);", "Chores"),;

    private final String color;
    private final String name;

    Label(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}