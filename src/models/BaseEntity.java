package models;

import java.util.Date;

public abstract class BaseEntity {
    private long id;
    private String name;

    private String addDate;
    private boolean active;

    public BaseEntity() {}
    public BaseEntity(long id, String name, String addDate, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.addDate = addDate;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "| name: " + name +
                "| addDate: " + addDate +
                "| active: " + active + " ";
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}

    public String getAddDate() {return addDate;}
    public void setAddDate(String addDate) {this.addDate = addDate;}
}
