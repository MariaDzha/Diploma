import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TransferPage {


    public void openTransferPage(String url){
        open (url);
    }



//    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
//    private SelenideElement monthField = $("[placeholder='08']");
//    private SelenideElement yearField = $("[placeholder='22']");
//    private ElementsCollection nameField = (ElementsCollection) $$("[class='input__box'] input[class='input__control']").get(3);
//    private SelenideElement CVVField= $("[placeholder='999']");
//    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
//    private SelenideElement errorMessage = $("div").find(withText("Ошибка"));
//    private SelenideElement successMessage = $("div").find(withText("Успешно"));



}
