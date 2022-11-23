package services.impl;

import models.*;
import services.*;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CrudServicesImpl implements CrudServices {
    GoodServices goodServices = new GoodServicesImpl();
    StoreServices storeServices = new StoreServicesImpl();
    CheckServices checkServices = new CheckServicesImpl();
    WorkerServices workerServices = new WorkerServicesImpl();
    CheckGoodServices checkGoodServices = new CheckGoodServicesImpl();
    CategoriesServices categoriesServices = new CategoriesServicesImpl();
    StoreCategoriesServices storeCategoriesServices = new StoreCategoriesServicesImpl();

    long maxId = 1;

    Scanner scn = new Scanner(System.in);
    private void infoForChoose(){
        System.out.println("Выберите действие ");
        System.out.println("1. Сохранить");
        System.out.println("2. Редактировать");
        System.out.println("3. Вывести все");
        System.out.println("4. Вывести по ID");
        System.out.println("5. Удалить по ID\n");
        System.out.print("Ответ: ");
    }

    @Override
    public void getStoreControl() {
        try {
            maxId = storeServices.findAll().stream()
                    .max(Comparator.comparing(Store::getId)).orElseThrow(NoSuchElementException::new).getId();
        } catch (Exception e){
            e.getMessage();
        }
        infoForChoose();

        switch (scn.nextInt()){
            case 1:
                scn.nextLine();
                System.out.print("\nНазвание магазина: ");
                String name = scn.nextLine();
                storeServices.save(name);

                System.out.println("\nМагазин сохранен!");
                storeServices.findAll().forEach(System.out::println);
                break;

            case 2:
                storeServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextLong();
                    scn.nextLine();
                }
                    System.out.print("\nНовое название: ");
                    name = scn.nextLine();
                    storeServices.update(id, name);

                    System.out.println("\nИзменения внесены: \n" + storeServices.findByID(id));
                break;

            case 3:
                System.out.println();
                storeServices.findAll().forEach(System.out::println);
                break;
            case 4:
                System.out.print("ID: ");
                System.out.println(storeServices.findByID(scn.nextLong()));
                break;

            case 5:
                storeServices.findAll().forEach(System.out::println);
                System.out.print("ID: ");
                storeServices.delete(scn.nextLong());
                break;
            default:
                System.out.println("Неверная команда!");
        }

    }

    @Override
    public void getWorkerControl() {
        try {
            maxId = workerServices.findAll().stream()
                    .max(Comparator.comparing(Worker::getId)).orElseThrow(NoSuchElementException::new).getId();
        }catch (Exception e) {
            e.getMessage();
        }

        infoForChoose();

        switch (scn.nextInt()){
            case 1:
                scn.nextLine();
                System.out.print("\nФИО: ");
                String full_name = scn.nextLine();

                System.out.print("\nlogin: ");
                String login = scn.next();

                System.out.print("\npassword: ");
                String password = scn.next();

                System.out.println();
                storeServices.findAll().stream().forEach(System.out::println);
                System.out.print("\nID магазина: ");
                long idTbStore = scn.nextLong();

                while (idTbStore > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    idTbStore = scn.nextLong();
                    scn.nextLine();
                }

                workerServices.save(full_name, login, password, idTbStore);

                System.out.println("\nПользователь сохранен: ");
                workerServices.findAll().stream().forEach(System.out::println);

                break;

            case 2:
                scn.nextLine();
                workerServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextLong();
                    scn.nextLine();
                }

                System.out.print("\nФИО: ");
                full_name = scn.nextLine();

                System.out.print("\nlogin: ");
                login = scn.next();

                System.out.print("\npassword: ");
                password = scn.next();

                storeServices.findAll().stream().forEach(System.out::println);
                System.out.print("\nID магазина: ");
                idTbStore = scn.nextInt();

                workerServices.update(id, full_name, login, password, idTbStore);

                System.out.println("Изменения внесены: \n" + workerServices.findById(id));
                break;

            case 3:
                System.out.println();
                workerServices.findAll().forEach(System.out::println);
                break;

            case 4:
                scn.nextLine();
                System.out.print("\nID: ");
                System.out.println(workerServices.findById(scn.nextLong()));
                break;

            case 5:
                workerServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                workerServices.delete(scn.nextLong());
                break;
            default:
                System.out.println("Неверная команда!");
        }
    }

    @Override
    public void getCategoriesControl() {
        try {
            maxId = categoriesServices.findAll().stream()
                    .max(Comparator.comparing(Categories::getId)).orElseThrow(NoSuchElementException::new).getId();
        }catch (Exception e) {
            e.getMessage();
        }
        infoForChoose();

        switch (scn.nextInt()){
            case 1:
                scn.nextLine();
                System.out.print("\nНазвание категории: ");
                categoriesServices.save(scn.nextLine());

                System.out.println("\nКатегория сохранена!");
                categoriesServices.findAll().forEach(System.out::println);
                break;

            case 2:
                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextInt();
                    scn.nextLine();
                }

                System.out.print("\nНовое название категории: ");
                String name = scn.nextLine();
                categoriesServices.update(id, name);

                System.out.println("\nИзменения сохранены: \n" + categoriesServices.findByID(id));
                break;

            case 3:
                System.out.println();
                categoriesServices.findAll().stream().forEach(System.out::println);
                break;
            case 4:
                System.out.print("\nID: ");
                System.out.println(categoriesServices.findByID(scn.nextLong()));
                break;

            case 5:
                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                categoriesServices.delete(scn.nextLong());
                break;
            default:
                System.out.println("\nНеверная команда!");
        }

    }

    @Override
    public void getGoodControl() {
        try {
            maxId = goodServices.findAll().stream()
                    .max(Comparator.comparing(Good::getId)).orElseThrow(NoSuchElementException::new).getId();
        }catch (Exception e){
            e.getMessage();
        }
        infoForChoose();

        switch (scn.nextInt()){
            case 1:
                scn.nextLine();
                System.out.print("\nНазвание товара: ");
                String name = scn.nextLine();

                System.out.print("\nЦена: ");
                double price = scn.nextDouble();

                System.out.print("\nСкидка: ");
                int discount = scn.nextInt();
                scn.nextLine();

                System.out.println();
                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nКатегория товара: ");
                long idTbCategories = scn.nextLong();
                goodServices.save(name, price, discount, idTbCategories);

                System.out.println("\nТовар сохранен: ");
                goodServices.findAll().forEach(System.out::println);
                break;

            case 2:
                scn.nextLine();
                goodServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextInt();
                    scn.nextLine();
                }

                System.out.print("\nНовое название товара: ");
                name = scn.nextLine();

                System.out.print("\nЦена: ");
                price = scn.nextDouble();

                System.out.print("\nСкидка: ");
                discount = scn.nextInt();
                scn.nextLine();

                System.out.println();
                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nКатегория: ");
                idTbCategories = scn.nextLong();
                goodServices.update(id, name, price, discount, idTbCategories);

                System.out.println("\nИзменения внесены: " + goodServices.findById(id));
                break;

            case 3:
                System.out.println();
                goodServices.findAll().stream().forEach(System.out::println);
                break;

            case 4:
                scn.nextLine();
                System.out.print("\nID: ");
                System.out.println(goodServices.findById(scn.nextLong()));
                break;

            case 5:
                goodServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                goodServices.delete(scn.nextLong());
                break;
            default:
                System.out.println("\nНеверная команда!");
        }
    }

    @Override
    public void getStoreCategoriesControl() {
        long maxIdStore = 1;
        long maxIdCategories = 1;

        try {
            maxId = storeCategoriesServices.findAll().stream()
                    .max(Comparator.comparing(StoreCategories::getId)).orElseThrow(NoSuchElementException::new).getId();

            maxIdStore = storeServices.findAll().stream()
                    .max(Comparator.comparing(Store::getId)).orElseThrow(NoSuchElementException::new).getId();

            maxIdCategories = categoriesServices.findAll().stream()
                    .max(Comparator.comparing(Categories::getId)).orElseThrow(NoSuchElementException::new).getId();

        } catch (Exception e){
            e.getMessage();
        }

        infoForChoose();

        switch (scn.nextInt()){
            case 1:
                storeServices.findAll().forEach(System.out::println);
                System.out.print("\nID магазина: ");
                long idTbStore = scn.nextLong();
                scn.nextLine();

                while (idTbStore > maxIdStore){
                    System.out.print("\nНеверное ID! \nID: ");
                    idTbStore= scn.nextInt();
                    scn.nextLine();
                }

                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID категории: ");
                long idTbCategories = scn.nextLong();

                while (idTbCategories > maxIdCategories){
                    System.out.print("\nНеверное ID! \nID: ");
                    idTbCategories = scn.nextInt();
                    scn.nextLine();
                }

                storeCategoriesServices.save(idTbStore, idTbCategories);

                System.out.println("\nСохранено!");
                storeCategoriesServices.findAll().stream().forEach(System.out::println);
                break;

            case 2:
                storeCategoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextInt();
                    scn.nextLine();
                }

                System.out.println();
                storeServices.findAll().forEach(System.out::println);
                System.out.print("\nID магазина: ");
                idTbStore = scn.nextLong();
                scn.nextLine();

                while (idTbStore > maxIdStore){
                    System.out.print("\nНеверное ID! \nID: ");
                    idTbStore= scn.nextInt();
                    scn.nextLine();
                }

                System.out.println();
                categoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID категории: ");
                idTbCategories = scn.nextLong();

                while (idTbCategories > maxIdCategories){
                    System.out.print("\nНеверное ID! \nID: ");
                    idTbCategories = scn.nextInt();
                    scn.nextLine();
                }

                storeCategoriesServices.update(id, idTbStore, idTbCategories);

                System.out.println("\nИзменение вненсено: "+ storeCategoriesServices.findById(id));
                break;

            case 3:
                System.out.println();
                storeCategoriesServices.findAll().stream().forEach(System.out::println);
                break;

            case 4:
                System.out.print("\nID: ");
                System.out.println(storeCategoriesServices.findById(scn.nextLong()));
                break;

            case 5:
                storeCategoriesServices.findAll().forEach(System.out::println);
                System.out.print("\nID");
                storeCategoriesServices.delete(scn.nextLong());
                break;
            default:
                System.out.print("\nНеверная команда!");
        }
    }
    @Override
    public void getCheckControl() {
        try {
            maxId = checkServices.findAll().stream()
                    .max(Comparator.comparing(Check::getId)).orElseThrow(NoSuchElementException::new).getId();
        } catch (Exception e) {
            e.getMessage();
        }
        infoForChoose();

        switch (scn.nextInt()) {
            case 1:
                scn.nextLine();
                System.out.print("\nОбщая сумма: ");
                checkServices.save(scn.nextDouble());

                System.out.println("\nЧек сохранен: ");
                checkServices.findAll().forEach(System.out::println);
                break;

            case 2:
                checkServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextInt();
                    scn.nextLine();
                }

                System.out.print("\nОбщая сумма: ");
                double totalSum = scn.nextDouble();
                checkServices.update(id, totalSum);

                System.out.println("\nИзменения: " + checkServices.findById(id));
                break;

            case 3:
                System.out.println();
                checkServices.findAll().stream().forEach(System.out::println);
                break;

            case 4:
                System.out.print("\nID: ");
                System.out.println(checkServices.findById(scn.nextLong()));
                break;

            case 5:
                checkServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                checkServices.delete(scn.nextLong());
                break;
            default:
                System.out.println("\nНеверная команда!");
        }
    }

    @Override
    public void getCheckGoodControl() {
        try {
            maxId = checkGoodServices.findAll().stream()
                    .max(Comparator.comparing(CheckGood::getId)).orElseThrow(NoSuchElementException::new).getId();
        }catch (Exception e) {
            e.getMessage();
        }
        infoForChoose();

        switch (scn.nextInt()) {
            case 1:
                scn.nextLine();
                checkServices.findAll().forEach(System.out::println);
                System.out.print("\nID чека: ");
                long idTbChecks = scn.nextLong();
                scn.nextLine();

                System.out.println();
                goodServices.findAll().forEach(System.out::println);
                System.out.print("\nID продукта: ");
                long idTbGoods = scn.nextLong();
                scn.nextLine();

                System.out.print("\nКоличество: ");
                int count = scn.nextInt();
                checkGoodServices.save(idTbChecks, idTbGoods, count);

                System.out.println("\nЧек продукта сохранен: ");
                checkGoodServices.findAll().forEach(System.out::println);
                break;

            case 2:
                checkGoodServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                long id = scn.nextLong();
                scn.nextLine();

                while (id > maxId){
                    System.out.print("\nНеверное ID! \nID: ");
                    id = scn.nextInt();
                    scn.nextLine();
                }

                System.out.println();
                checkServices.findAll().forEach(System.out::println);
                System.out.print("\nID чека: ");
                idTbChecks = scn.nextLong();
                scn.nextLine();

                System.out.println();
                goodServices.findAll().forEach(System.out::println);
                System.out.print("\nID продукта: ");
                idTbGoods = scn.nextLong();
                scn.nextLine();

                System.out.print("\nКоличество: ");
                count = scn.nextInt();

                checkGoodServices.update(id, idTbChecks, idTbGoods, count);

                System.out.println("\nИзменения внесены: " + checkGoodServices.findById(id));
                break;

            case 3:
                System.out.println();
                checkGoodServices.findAll().stream().forEach(System.out::println);
                break;

            case 4:
                checkServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                System.out.println(checkGoodServices.findById(scn.nextLong()));
                break;

            case 5:
                checkGoodServices.findAll().forEach(System.out::println);
                System.out.print("\nID: ");
                checkGoodServices.delete(scn.nextLong());
                break;

            default:
                System.out.println("\nНеверная команда!");

        }
    }
}
