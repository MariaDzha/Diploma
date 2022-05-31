import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class TravelShopTest {

    @Test
    void shouldBuy() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        //check values in db
        DBTester DBTester = new DBTester();
        DBTester.checkLastOrder("APPROVED" ,45000);
    }

    @Test
    void shouldBuyCredit() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester DBTester = new DBTester();
        DBTester.checkLastOrderCredit("APPROVED");
    }

    @Test
    void shouldNotBuyByDeclinedCard() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4442");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester DBTester = new DBTester();
        DBTester.checkLastOrder("DECLINED" ,null);
    }

    @Test
    void shouldNotBuyMonthInPast() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("04");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyUnrealMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("13");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyZeroMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("00");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyYearInPast() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("21");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyPlusSixYears() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("28");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyYearZero() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("00");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldYear() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCyrillicName() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Иванов Иван");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithFigures() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("11111");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithStops() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("!!!!!");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldName() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVLessFigures() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("00");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldCVV() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVZero() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("000");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyByCardNotFromList() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("1234 1234 1234 1234");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotBuyCreditByDeclinedCard() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4442");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester DBTester = new DBTester();
        DBTester.checkLastOrderCredit("DECLINED");
    }

    @Test
    void shouldNotBuyCreditMonthInPast() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("04");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditUnrealMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("13");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditZeroMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("00");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditYearInPast() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("21");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditPlusSixYears() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("28");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyCreditYearZero() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("00");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldYear() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCyrillicName() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Иванов Иван");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithFigures() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("11111");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithStops() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("!!!!!");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldName() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVLessFigures() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("00");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldCVV() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVZero() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("000");
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditByCardNotFromList() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("1234 1234 1234 1234");
        $("[placeholder='08']").setValue("08");
        $("[placeholder='22']").setValue("22");
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue("Ivanov Ivan");
        $("[placeholder='999']").setValue("999");
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
