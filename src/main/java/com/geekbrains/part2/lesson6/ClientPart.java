package com.geekbrains.part2.lesson6;

import java.io.IOException;
import java.util.Scanner;

public class ClientPart {
    public static Scanner sc = new Scanner(System.in);
    private final Controller controller = new Controller();
    public String period;
    public String city;

    public void userInput() {
        System.out.println("Вас приветствует клиент по получению прогноза погоды.");
        while(true) {
            System.out.println("Для получения проноза погоды введите город или введите \"exit\" для выхода из программы.\n" +
                    "Если вы хотите получить прогноз погоды для Санкт-Петербурга, то введите \"SPB\"");
            city = sc.nextLine();

            if (city.equalsIgnoreCase("exit")) {break;}
            if (city.equalsIgnoreCase("SPB")) {ApplicationGlobalState.getInstance().setSelectedCity("Saint Petersburg");}
            else {ApplicationGlobalState.getInstance().setSelectedCity(city);}

            inputPeriod();
            System.out.println(city + " " + period);
            if (isInputValid(city, period)) {
                try {
                    controller.onUserInput(period);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Что-то пошло не так. Попробуйте ввести нужный город снова");
                }
            }
        }
    }

    private void inputPeriod() {
        System.out.println("Введите требуемый период прогноза: 1 - прогноз на 1 день, 2 - прогноз на 5 дней");
        period = sc.nextLine();
    }

    public static boolean isInputValid (String city, String period) {
        if (period.isEmpty() || city.isEmpty()) {
            System.out.println("Введенное значение не может быть пустым");
            return false;
        }

        if (period.equals("1") || period.equals("2")) return true;

        System.out.println("Неверный ввод");
        return false;
    }
}
