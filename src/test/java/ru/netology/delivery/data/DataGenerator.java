package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Getter
public class DataGenerator {
    private static String name2;

    public static String getName2() {
        return name2;
    }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static class Registration {
    }

    public DataGenerator() {
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String getRandomCity() {
        String city = faker.address().city();
        // TODO: добавить логику для объявления переменной login и задания её значения, для генерации
        //  случайного логина используйте faker
        return city;
    }

    public static String getRandomName() {
        String name = faker.name().name();
        // TODO: добавить логику для объявления переменной password и задания её значения, для генерации
        //  случайного пароля используйте faker
        return name;
    }

    public static String getRandomPhone() {
        String phone = faker.phoneNumber().phoneNumber();
        // TODO: добавить логику для объявления переменной password и задания её значения, для генерации
        //  случайного пароля используйте faker
        return phone;
    }

    public static RegistrationInfo getUser() {
        RegistrationInfo user = new RegistrationInfo(getRandomCity(), getRandomName(), getRandomPhone());
        // TODO: создать пользователя user используя методы getRandomLogin(), getRandomPassword() и параметр status
        return user;
    }

    public void searchNameYo() {

        int index = 0;
        String st = getRandomName();
        System.out.println(st);
        char[] c = st.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'ё') {

                index++;
            }
        }
        if (index > 0) {
            this.name2 = st;

        } else searchNameYo();

    }

    public static RegistrationInfo getUserYo() {
        RegistrationInfo user = new RegistrationInfo(getRandomCity(), getName2(), getRandomPhone());
        // TODO: создать пользователя user используя методы getRandomLogin(), getRandomPassword() и параметр status

        return user;
    }


}
