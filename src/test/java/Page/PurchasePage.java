package Page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PurchasePage {

    SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    SelenideElement monthField = $("[placeholder='08']");
    SelenideElement yearField = $("[placeholder='22']");
    ElementsCollection TextField = $$("[class='input__box'] input[class='input__control']");
    SelenideElement CVVField= $("[placeholder='999']");

    public void fillAllFields (String cardNumber, String month, String year, String name, String CVV) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        TextField.get(3).setValue(name);
        CVVField.setValue(CVV);
    }

    public void clickContinueButton(){
        SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
        continueButton.click();
    }

    public void shouldShowSuccessMessage(){
        SelenideElement successMessage = $("div").find(withText("Успешно"));
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldNotShowSuccessMessage(){
        SelenideElement successMessage = $("div").find(withText("Успешно"));
        successMessage.shouldNot(appear);
    }

    public void shouldShowErrorMessage(){
        SelenideElement errorMessage = $("div").find(withText("Ошибка"));
        errorMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldNotShowErrorMessage(){
        SelenideElement errorMessage = $("div").find(withText("Ошибка"));
        errorMessage.shouldNot(appear);
    }

    public void shouldShowWrongCardTermText(){
        SelenideElement wrongCardTerm = $$("[class='input__sub']").findBy(text("Неверно указан срок действия карты"));
        wrongCardTerm.shouldBe(visible);
    }

    public void shouldShowWrongFormatText(){
        SelenideElement wrongFormat = $$("[class='input__sub']").findBy(text("Неверный формат"));
        wrongFormat.shouldBe(visible);
    }

    public void shouldShowPassedCardTermText(){
        SelenideElement passedCardTerm = $$("[class='input__sub']").findBy(text("Истёк срок действия карты"));
       passedCardTerm.shouldBe(visible);
    }
    public void shouldShowNameErrorText(){
        SelenideElement nameError = $("[class='input__sub']");
        nameError.shouldBe(visible);
    }

    public void shouldShowObligatoryNameText(){
        SelenideElement obligatoryNameText = $$("[class='input__sub']").findBy(text("Поле обязательно для заполнения"));
        obligatoryNameText.shouldBe(visible);
    }

}
