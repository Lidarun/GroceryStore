package models;

import services.GetNameById;

public class Good extends BaseEntity {
    private double price;
    private int discount;
    private long idCategories;

    GetNameById getNameById = new GetNameById();

    public Good(){};
    public Good(long id, String name, String addDate, boolean active, double price, int discount, long idCategories) {
        super(id, name, addDate, active);
        this.price = price;
        this.discount = discount;
        this.idCategories = idCategories;
    }

    @Override
    public String toString() {
        return  "ID: " + super.getId() +
                "| name: " + super.getName() +
                "| price: " + price + "$"+
                "| discount: " + discount + "%"+
                "| idCategories: " + idCategories +" ("+getNameById.name(idCategories, "tb_categories")+")" +
                "| addDate: " + super.getAddDate() +
                "| active: " + super.isActive();
    }

    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getDiscount() {return discount;}
    public void setDiscount(int discount) {this.discount = discount;}

    public long getIdCategories() {return idCategories;}
    public void setIdCategories(long idCategories) {this.idCategories = idCategories;}
}
