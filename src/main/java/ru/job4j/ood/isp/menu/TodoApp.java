package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    private static final Menu MENU = new SimpleMenu();

    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action...");

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        while (run) {
            showMenu();
            int answer = Integer.parseInt(input.nextLine());
            String name;

            switch (answer) {
                case 1:
                    System.out.print("Введите название элемента: ");
                    name = input.nextLine();
                    MENU.add(Menu.ROOT, name, DEFAULT_ACTION);
                    System.out.println("Элемент успешно добавлен.");
                    break;
                case 2:
                    System.out.print("Введите название родительского элемента: ");
                    String parent = input.nextLine();
                    System.out.print("Введите название элемента: ");
                    name = input.nextLine();
                    MENU.add(parent, name, DEFAULT_ACTION);
                    System.out.println("Элемент успешно добавлен");
                    break;
                case 3:
                    System.out.print("Введите название элемента: ");
                    name = input.nextLine();
                    Optional<Menu.MenuItemInfo> item = MENU.select(name);
                    item.ifPresent(menuItemInfo -> menuItemInfo.getActionDelegate().delegate());
                    break;
                case 4:
                    MENU.forEach(i -> System.out.println(i.getNumber() + i.getName()));
                    break;
                case 5:
                    run = false;
                    break;
                default:
                    System.out.println("Неверный пункт. Введите 1–5");

            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Добавить элемент в корень меню");
        System.out.println("2. Добавить элемент к родительскому элементу");
        System.out.println("3. Вызвать действие, привязанное к пункту меню");
        System.out.println("4. Вывести все пункты меню");
        System.out.println("5. Выход");
        System.out.print("Введите число от 1 до 5: ");
    }
}
