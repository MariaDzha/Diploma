import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;



public class TravelShopTest {

    @Test
    void shouldBuy() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();


        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("APPROVED", dbTester.getLastStatus());
        Assertions.assertEquals(45000, dbTester.getLastAmount());
    }

    @Test
    void shouldBuyCredit() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("div").find(withText("Ошибка")).shouldNot(appear);
        $("div").find(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("APPROVED", dbTester.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyByDeclinedCard() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberDeclined());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("DECLINED", dbTester.getLastStatus());


    }

    @Test
    void shouldNotBuyMonthInPast() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(-1, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(0, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyUnrealMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getUnrealMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyZeroMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getZeroMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getCleanFieldMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyYearInPast() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(-1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyPlusSixYears() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(6, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyYearZero() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.getZeroYear());
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldYear() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.getCleanFieldYear());
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCyrillicName() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getCyrillicName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithFigures() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getNameWithFigures());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyNameWithStops() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getNameWithStops());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldName() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getCleanFieldName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVLessFigures() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCVVLessFigures());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCleanFieldCVV() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCleanFieldCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCVVZero() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCVVZero());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyByCardNotFromList() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberNotFromList());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotBuyCreditByDeclinedCard() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberDeclined());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));

        DBTester dbTester = new DBTester();
        Assertions.assertEquals("DECLINED", dbTester.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyCreditMonthInPast() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(-1, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(0, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditUnrealMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getUnrealMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditZeroMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getZeroMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldMonth() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.getCleanFieldMonth());
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditYearInPast() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(-1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditPlusSixYears() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(6, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }
    @Test
    void shouldNotBuyCreditYearZero() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.getZeroYear());
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldYear() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.getCleanFieldYear());
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCyrillicName() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getCyrillicName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithFigures() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getNameWithFigures());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditNameWithStops() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getNameWithStops());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldName() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getCleanFieldName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVLessFigures() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCVVLessFigures());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCleanFieldCVV() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCleanFieldCVV());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditCVVZero() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberApproved());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getCVVZero());
        transferPage.clickContinueButton();
        $("[class='input__sub']").shouldBe(visible);
    }

    @Test
    void shouldNotBuyCreditByCardNotFromList() {
        TransferPage transferPage = new TransferPage();
        transferPage.openTransferPage("http://localhost:8080");
        transferPage.clickBuyInCreditButton();
        transferPage.fillCardNumber(DataGenerator.getCardNumberNotFromList());
        transferPage.fillMonthField(DataGenerator.generateMonth(2, "MM"));
        transferPage.fillYearField(DataGenerator.generateYear(1, "YY"));
        transferPage.fillNameField(DataGenerator.getRightName());
        transferPage.fillCVVField(DataGenerator.getRightCVV());
        transferPage.clickContinueButton();
        $("div").find(withText("Успешно")).shouldNot(appear);
        $("div").find(withText("Ошибка")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
