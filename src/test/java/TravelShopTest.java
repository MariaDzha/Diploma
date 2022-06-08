import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Assertions;
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
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("APPROVED", dbTester.getLastStatus());
        Assertions.assertEquals(45000, dbTester.getLastAmount());
    }

    @Test
    void shouldBuyCredit() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("APPROVED", dbTester.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyByDeclinedCard() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberDeclined());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("DECLINED", dbTester.getLastStatus());


    }

    @Test
    void shouldNotBuyMonthInPast() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getMonthFromPast());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyUnrealMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getUnrealMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyZeroMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getZeroMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldMonth() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getCleanFieldMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyYearInPast() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getYearInPast());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyPlusSixYears() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getPlusSixYears());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyYearZero() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getZeroYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldYear() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getCleanFieldYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCyrillicName() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getCyrillicName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithFigures() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getNameWithFigures());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithStops() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getNameWithStops());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldName() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getCleanFieldName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVLessFigures() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCVVLessFigures());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldCVV() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCleanFieldCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVZero() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCVVZero());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyByCardNotFromList() {
        open ("http://localhost:8080");
        $(withText("Купить")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberNotFromList());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotBuyCreditByDeclinedCard() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberDeclined());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("DECLINED", dbTester.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyCreditMonthInPast() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getMonthFromPast());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditUnrealMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getUnrealMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditZeroMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getZeroMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldMonth() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getCleanFieldMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditYearInPast() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getYearInPast());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditPlusSixYears() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getPlusSixYears());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyCreditYearZero() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getZeroYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldYear() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getCleanFieldYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCyrillicName() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getCyrillicName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithFigures() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getNameWithFigures());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithStops() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getNameWithStops());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldName() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getCleanFieldName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVLessFigures() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCVVLessFigures());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldCVV() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCleanFieldCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVZero() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberApproved());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getCVVZero());
        $$("button").find(exactText("Продолжить")).click();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditByCardNotFromList() {
        open ("http://localhost:8080");
        $(withText("Купить в кредит")).click();
        $("[placeholder='0000 0000 0000 0000']").setValue(DataGenerator.getCardNumberNotFromList());
        $("[placeholder='08']").setValue(DataGenerator.getRightMonth());
        $("[placeholder='22']").setValue(DataGenerator.getRightYear());
        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
        TextField.get(3).setValue(DataGenerator.getRightName());
        $("[placeholder='999']").setValue(DataGenerator.getRightCVV());
        $$("button").find(exactText("Продолжить")).click();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
