package models;

import services.CheckServices;
import services.GoodServices;
import services.impl.CheckServicesImpl;
import services.impl.GoodServicesImpl;

public class CheckGood{
    private long id;
    private Check idCheck;
    private Good idGood;
    private int count;
    private boolean active;

    public CheckGood(){}

    @Override
    public String toString() {
        CheckServices checkServices = new CheckServicesImpl();
        GoodServices goodServices = new GoodServicesImpl();

        return "ID: " + id +
                "| idCheck: " + idCheck.getId() +
                "| idGood: " + idGood.getId() +
                "| count: " + count +
                "| active: " + active +
                "\n   Check: " + checkServices.findById(idCheck.getId())+
                "\n               Good: " + goodServices.findById(idGood.getId());
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public Check getIdCheck() {return idCheck;}
    public void setIdCheck(Check idCheck) {this.idCheck = idCheck;}

    public Good getIdGood() {return idGood;}
    public void setIdGood(Good idGood) {this.idGood = idGood;}

    public int getCount() {return count;}
    public void setCount(int count) {this.count = count;}

    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
}
