package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        assertThat(new Menu.MenuItemInfo("Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());

        assertThat(new Menu.MenuItemInfo("Купить продукты", List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());

        assertThat(new Menu.MenuItemInfo("Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());

        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenSelectNotFoundThenEmpty() {
        Menu menu = new SimpleMenu();
        assertThat(menu.select("Нет такого")).isEmpty();
    }

    @Test
    public void whenSelectRootThenCorrectInfo() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "A", STUB_ACTION);
        menu.add(Menu.ROOT, "B", STUB_ACTION);
        menu.add("A", "A1", STUB_ACTION);
        Menu.MenuItemInfo expected = new Menu.MenuItemInfo("A", List.of("A1"), STUB_ACTION, "1.");
        assertThat(menu.select("A")).contains(expected);
    }

    @Test
    public void whenSelectNestedThenCorrectInfoAndChildrenOrder() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "A", STUB_ACTION);
        menu.add("A", "A1", STUB_ACTION);
        menu.add("A1", "A1.1", STUB_ACTION);
        menu.add("A1", "A1.2", STUB_ACTION);
        Menu.MenuItemInfo expected = new Menu.MenuItemInfo("A1", List.of("A1.1", "A1.2"), STUB_ACTION, "1.1.");
        assertThat(menu.select("A1")).contains(expected);
    }
}