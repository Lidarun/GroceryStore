package models;

import services.GetNameById;

public class StoreCategories {
    private long id;
    private long idStore;
    private long idCategories;
    private boolean active;

    GetNameById getNameById = new GetNameById();

    public StoreCategories(){}
    public StoreCategories(long id, long idStore, long idCategories, boolean active) {
        this.id = id;
        this.idStore = idStore;
        this.idCategories = idCategories;
        this.active = active;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "| idStore: " + idStore + " ("+getNameById.name(idStore, "tb_store") +")" +
                "| idCategories: " + idCategories +" ("+ getNameById.name(idCategories, "tb_categories") + ")" +
                "| active: " + active;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public long getIdStore() {return idStore;}
    public void setIdStore(long idStore) {this.idStore = idStore;}

    public long getIdCategories() {return idCategories;}
    public void setIdCategories(long idCategories) {this.idCategories = idCategories;}

    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active = active;}
}
