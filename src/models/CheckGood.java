package models;

public class CheckGood{
    private long id;
    private long idCheck;
//    private Check checkID;
//    private Good goodID;
    private long idGood;
    private int count;
    private boolean active;

    public CheckGood(){}
    public CheckGood(long id, long idCheck, long idGood, int count, boolean active) {
        this.id = id;
        this.idCheck = idCheck;
        this.idGood = idGood;
        this.count = count;
        this.active = active;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "| idCheck: " + idCheck +
                "| idGood: " + idGood +
                "| count: " + count +
                "| active: " + active + "\n";
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public long getIdCheck() {return idCheck;}
    public void setIdCheck(long idCheck) {this.idCheck = idCheck;}

    public long getIdGood() {return idGood;}
    public void setIdGood(long idGood) {this.idGood = idGood;}

    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}

    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
}
