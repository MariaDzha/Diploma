public class DataGenerator {

    public static String getCardNumberApproved() {
        return "4444 4444 4444 4441";
    }

    public static String getCardNumberDeclined() {

        return "4444 4444 4444 4442";
    }

    public static String getCardNumberNotFromList() {

        return "1234 1234 1234 1234";
    }

    public static String getRightMonth() {

        return "08";
    }

    public static String getMonthFromPast() {

        return "04";
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

    public static String getRightYear() {

        return "22";
    }
    public static String getYearInPast() {

        return "21";
    }

    public static String getPlusSixYears() {

        return "28";
    }

    public static String getZeroYear() {

        return "00";
    }

    public static String getCleanFieldYear() {

        return "";
    }

    public static String getRightName() {

        return "Ivanov Ivan";
    }
    public static String getCyrillicName() {

        return "Иванов Иван";
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

        return "999";
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
