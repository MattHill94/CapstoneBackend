package project.troops;

//Model of the trooper, this model will be used to hold information of troopers imported from the XML file
public class Player {
    private String name;
    private int num;
    private int percentage;

    public Player(String name, int num, int percentage) {
        this.name = name;
        this.num = num;
        this.percentage = percentage;
    }
    //getters - used to access information of the trooper.
    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getPercentage() {
        return percentage;
    }
}
