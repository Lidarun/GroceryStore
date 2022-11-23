package models;

import DAO.DBHelper;
import DAO.iml.DBHelperImpl;
import services.GetNameById;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Worker extends BaseEntity {
    private String login;
    private String password;
    private long idStore;
//    private Store idStore;

    GetNameById getNameById = new GetNameById();

    public Worker(){};
    public Worker(long id, String name, String addDate, boolean active, String login, String password, long idStore) {
        super(id, name, addDate, active);
        this.login = login;
        this.password = password;
        this.idStore = idStore;
    }

    @Override
    public String toString() {
        return  "ID: " + super.getId() +
                "| fullName: " + super.getName() +
                "| login: " + login +
                "| password: " + password +
                "| idStore: " + idStore + " ("+getNameById.name(idStore, "tb_store")+")"+
                "| addDate: " + super.getAddDate() +
                "| active: " + super.isActive();
    }

    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public long getIdStore() {return idStore;}
    public void setIdStore(long idStore) {
        this.idStore = idStore;
    }
}
