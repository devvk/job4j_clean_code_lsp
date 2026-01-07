package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    /**
     * 1. Задача Общая.
     * ----1.1. Задача Первая.
     * ---------1.1.1. Цель задачи.
     * ---------1.1.2. Ограничения и требования к решению.
     * ----1.2. Задача Вторая.
     *
     * @param menu меню.
     */
    @Override
    public void print(Menu menu) {
        String delimiter = "----";
        for (Menu.MenuItemInfo item : menu) {
            String number = item.getNumber();
            int dotsCount = number.split("\\.").length - 1;
            System.out.println(delimiter.repeat(dotsCount) + number + " " + item.getName());
        }
    }
}
