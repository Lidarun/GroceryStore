import services.CrudServices;
import services.impl.CrudServicesImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CrudServices crudServices = new CrudServicesImpl();
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие ");
            System.out.println("1. Справочник ");
            System.out.println("2. Операции ");
            System.out.print("\nОтвет: ");

            if (scn.nextInt() == 1) {
                System.out.println("\nВыберите справочник ");
                System.out.println("1. Магазин");
                System.out.println("2. Пользователь");
                System.out.println("3. Категории");
                System.out.println("4. Продукты");
                System.out.println("5. Категория магазина");
                System.out.println("6. Чек");
                System.out.println("7. Чек продукта");
                System.out.print("\nОтвет: ");

                switch (scn.nextInt()) {
                    case 1 -> crudServices.getStoreControl();
                    case 2 -> crudServices.getWorkerControl();
                    case 3 -> crudServices.getCategoriesControl();
                    case 4 -> crudServices.getGoodControl();
                    case 5 -> crudServices.getStoreCategoriesControl();
                    case 6 -> crudServices.getCheckControl();
                    case 7 -> crudServices.getCheckGoodControl();
                    default -> System.out.println("\nНеверная команда!");
                }
            }
        }
    }
}