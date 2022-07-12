package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    public static String getCardNumberApproved() {
        return "4444 4444 4444 4441";
    }

    public static String getCardNumberDeclined() {

        return "4444 4444 4444 4442";
    }

    public static String getCardNumberNotFromList() {
        Faker faker;
        faker = new Faker();
        String cardNumber = faker.numerify("#### #### #### ####");
        return cardNumber;
    }

    public static String generateMonth(int months, String formatPattern) {
        return LocalDate.now().plusMonths(months).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getUnrealMonth() {

        return "13";
    }

    public static String getZeroMonth() {

        return "00";
    }
    public static String getCleanFieldMonth() {

        return "";
    }

    public static String generateYear(int years, String formatPattern) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern(formatPattern));
    }

    public static String getZeroYear() {

        return "00";
    }

    public static String getCleanFieldYear() {

        return "";
    }

    public static String getRightName() {
        Faker faker;
        faker = new Faker (new Locale("en"));
        String name = faker.name().fullName();
        return name;
    }
    public static String getCyrillicName() {
        Faker faker;
        faker = new Faker (new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    public static String getNameWithFigures() {

        return "11111";
    }
    public static String getNameWithStops() {

        return "!!!!!";
    }

    public static String getCleanFieldName() {

        return "";
    }

    public static String getRightCVV() {
        Faker faker;
        faker = new Faker ();
        String CVV = String.valueOf(faker.random().nextInt(100, 999));
        return CVV;
    }
    public static String getCVVLessFigures() {

        return "00";
    }

    public static String getCleanFieldCVV() {

        return "";
    }

    public static String getCVVZero() {

        return "000";
    }
}
