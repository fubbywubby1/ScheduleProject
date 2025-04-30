enum Label {
    WORK("-fx-background-color:rgb(249, 199, 64);"),
    SCHOOL("-fx-background-color:rgb(245, 91, 155);"),
    STUDY("-fx-background-color:rgb(200, 200, 200);"), // Example color
    CLUB("-fx-background-color:rgb(180, 180, 255);"), // Example color
    LEISURE("-fx-background-color:rgb(255, 200, 200);"), // Example color
    SELFCARE("-fx-background-color:rgb(154, 187, 246);"),
    CHORES("-fx-background-color:rgb(200, 200, 200);");

    private final String color;

    // Parameterized constructor
    Label(String color) {
        this.color = color;
    }

    // Getter for the color
    public String getColor() {
        return color;
    }
}