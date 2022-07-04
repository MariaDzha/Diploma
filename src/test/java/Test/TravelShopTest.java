package Test;
import Data.DBUtils;
import Data.DataGenerator;
import Page.CreditPage;
import Page.PurchasePage;
import Page.TransferPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;



public class TravelShopTest {

    @Test
    void shouldBuy() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();

        purchasePage.shouldShowSuccessMessage();
        purchasePage.shouldNotShowErrorMessage();

        Assertions.assertEquals("APPROVED", DBUtils.getLastStatus());
        Assertions.assertEquals(45000, DBUtils.getLastAmount());
    }

    @Test
    void shouldBuyCredit() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage = new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();

        creditPage.shouldShowSuccessMessage();
        creditPage.shouldNotShowErrorMessage();

        Assertions.assertEquals("APPROVED", DBUtils.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyByDeclinedCard() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberDeclined(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();

        purchasePage.shouldNotShowSuccessMessage();
        purchasePage.shouldShowErrorMessage();
        Assertions.assertEquals("DECLINED", DBUtils.getLastStatus());

    }

    @Test
    void shouldNotBuyMonthInPast() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(-1, "MM"),
                DataGenerator.generateYear(0, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());

        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongCardTermText();

    }

    @Test
    void shouldNotBuyUnrealMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getUnrealMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());

        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyZeroMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getZeroMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCleanFieldMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getCleanFieldMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongFormatText();


    }

    @Test
    void shouldNotBuyYearInPast() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(-1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowPassedCardTermText();
    }

    @Test
    void shouldNotBuyPlusSixYears() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(6, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyYearZero() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.getZeroYear(),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowPassedCardTermText();
    }

    @Test
    void shouldNotBuyCleanFieldYear() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.getCleanFieldYear(),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCyrillicName() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getCyrillicName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowNameErrorText();

    }

    @Test
    void shouldNotBuyNameWithFigures() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getNameWithFigures(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowNameErrorText();

    }

    @Test
    void shouldNotBuyNameWithStops() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getNameWithStops(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowNameErrorText();
    }

    @Test
    void shouldNotBuyCleanFieldName() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getCleanFieldName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowObligatoryNameText();
    }

    @Test
    void shouldNotBuyCVVLessFigures() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCVVLessFigures());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCleanFieldCVV() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCleanFieldCVV());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCVVZero() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCVVZero());
        purchasePage.clickContinueButton();
        purchasePage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyByCardNotFromList() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        PurchasePage purchasePage = new PurchasePage();
        transferPage.clickBuyButton();
        purchasePage.fillAllFields(
                DataGenerator.getCardNumberNotFromList(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        purchasePage.clickContinueButton();

        purchasePage.shouldNotShowSuccessMessage();
        purchasePage.shouldShowErrorMessage();
    }

    @Test
    void shouldNotBuyCreditByDeclinedCard() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberDeclined(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowErrorMessage();
        Assertions.assertEquals("DECLINED", DBUtils.getLastStatusCredit());
    }

    @Test
    void shouldNotBuyCreditMonthInPast() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(-1, "MM"),
                DataGenerator.generateYear(0, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCreditUnrealMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getUnrealMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCreditZeroMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getZeroMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCreditCleanFieldMonth() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.getCleanFieldMonth(),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCreditYearInPast() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(-1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowPassedCardTermText();
    }

    @Test
    void shouldNotBuyCreditPlusSixYears() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(6, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCreditYearZero() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.getZeroYear(),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowPassedCardTermText();

    }

    @Test
    void shouldNotBuyCreditCleanFieldYear() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.getCleanFieldYear(),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongCardTermText();
    }

    @Test
    void shouldNotBuyCreditCyrillicName() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getCyrillicName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowNameErrorText();
    }

    @Test
    void shouldNotBuyCreditNameWithFigures() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getNameWithFigures(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowNameErrorText();
    }

    @Test
    void shouldNotBuyCreditNameWithStops() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getNameWithStops(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowNameErrorText();
    }

    @Test
    void shouldNotBuyCreditCleanFieldName() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getCleanFieldName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowObligatoryNameText();
    }

    @Test
    void shouldNotBuyCreditCVVLessFigures() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCVVLessFigures());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCreditCleanFieldCVV() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCleanFieldCVV());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCreditCVVZero() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberApproved(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getCVVZero());
        creditPage.clickContinueButton();
        creditPage.shouldShowWrongFormatText();
    }

    @Test
    void shouldNotBuyCreditByCardNotFromList() {
        open("http://localhost:8080");
        TransferPage transferPage = new TransferPage();
        CreditPage creditPage =  new CreditPage();
        transferPage.clickBuyInCreditButton();
        creditPage.fillAllFields(
                DataGenerator.getCardNumberNotFromList(),
                DataGenerator.generateMonth(2, "MM"),
                DataGenerator.generateYear(1, "YY"),
                DataGenerator.getRightName(),
                DataGenerator.getRightCVV());
        creditPage.clickContinueButton();
        creditPage.shouldNotShowSuccessMessage();
        creditPage.shouldShowErrorMessage();
    }
}
