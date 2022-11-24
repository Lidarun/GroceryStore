package models;

import services.GetNameById;

public class Worker extends BaseEntity {
    private String login;
    private String password;
//    private long idStore;
    private Store idStore;

    GetNameById getNameById = new GetNameById();

    public Worker(){};

    @Override
    public String toString() {
        return  "ID: " + super.getId() +
                "| fullName: " + super.getName() +
                "| login: " + login +
                "| password: " + password +
                "| idStore: " + idStore.getId() + " ("+getNameById.name(idStore.getId(), "tb_store")+")"+
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

    public Store getIdStore() {return idStore;}
    public void setIdStore(Store idStore) {
        this.idStore = idStore;
    }
}
