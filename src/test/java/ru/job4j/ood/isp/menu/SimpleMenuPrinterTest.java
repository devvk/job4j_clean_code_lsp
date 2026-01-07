package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SimpleMenuPrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenPrintMenuThenCorrectOutput() {
        /*
         * 1. Задача Общая.
         * ----1.1. Задача Первая.
         * ---------1.1.1. Цель задачи.
         * ---------1.1.2. Ограничения и требования к решению.
         * ----1.2. Задача Вторая.
         */
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Задача Общая.", STUB_ACTION);
        menu.add("Задача Общая.", "Задача Первая.", STUB_ACTION);
        menu.add("Задача Первая.", "Цель задачи.", STUB_ACTION);
        menu.add("Задача Первая.", "Ограничения и требования к решению.", STUB_ACTION);
        menu.add("Задача Общая.", "Задача Вторая.", STUB_ACTION);

        MenuPrinter printer = new SimpleMenuPrinter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));

        try {
            printer.print(menu);
        } finally {
            System.setOut(old);
        }

        String ln = System.lineSeparator();
        String expected =
                "1. Задача Общая." + ln
                        + "----1.1. Задача Первая." + ln
                        + "--------1.1.1. Цель задачи." + ln
                        + "--------1.1.2. Ограничения и требования к решению." + ln
                        + "----1.2. Задача Вторая." + ln;

        assertThat(out.toString()).isEqualTo(expected);
    }

}