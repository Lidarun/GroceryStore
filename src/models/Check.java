package models;

public class Check {
    private long id;
    private double totalSum;
    private String addDate;
    private boolean active;

    public Check(){}
    public Check(long id, long totalSum, String addDate, boolean active) {
        this.id = id;
        this.totalSum = totalSum;
        this.addDate = addDate;
        this.active = active;
    }

    @Override
    public String toString() {
        return  "ID: "  + id +
                "| totalSum: " + totalSum + "$" +
                "| addDate: " + addDate +
                "| active: " + active;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public double getTotalSum() {return totalSum;}
    public void setTotalSum(double totalSum) {this.totalSum = totalSum;}

    public String getAddDate() {return addDate;}
    public void setAddDate(String addDate) {this.addDate = addDate;}

    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
}
