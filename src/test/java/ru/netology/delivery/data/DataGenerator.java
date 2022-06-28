package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;




public class DataGenerator {


    public static String generateDate(int shift) {
        Faker faker = new Faker(new Locale("ru"));
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String city = faker.options().option("Москва", "Санкт-Петербург", "Ульяновск", "Тула", "Липецк", "Казань", "Орёл", "Саранск", "Чебоксары", "Краснодар", "Екатеринбург");
        return city;
    }

    public static String generateInvalidCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String city = faker.address().city();
        return city;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generateNameWithYo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName().concat("ё");
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79#########");
        return phone;
    }

    public static String generateInvalidStartingPhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("###########");
        return phone;
    }

    public static String generateInvalidPhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+79#####");
        return phone;
    }



    public static class Registration {

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity(locale), generateName(locale), generatePhone(locale));
            return (UserInfo) user;
        }
    }



    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}