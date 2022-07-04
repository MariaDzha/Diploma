package Page;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {

    public void clickBuyButton(){
        SelenideElement buyButton = $(withText("Купить"));
        buyButton.click();
    }
    public void clickBuyInCreditButton(){
        SelenideElement buyInCreditButton = $(withText("Купить в кредит"));
        buyInCreditButton.click();
    }
//    public void fillCardNumber(String cardNumber){
//        SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
//        cardNumberField.setValue(cardNumber);
//    }
//
//    public void fillMonthField(String month){
//        SelenideElement monthField = $("[placeholder='08']");
//        monthField.setValue(month);
//    }
//    public void fillYearField(String year){
//        SelenideElement yearField = $("[placeholder='22']");
//        yearField.setValue(year);
//    }
//
//    public void fillNameField(String name){
//        ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
//        TextField.get(3).setValue(name);
//    }
//
//    public void fillCVVField(String CVV){
//        SelenideElement CVVField= $("[placeholder='999']");
//        CVVField.setValue(CVV);
//    }
//
//    public void clickContinueButton(){
//        SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
//        continueButton.click();
//    }

}
