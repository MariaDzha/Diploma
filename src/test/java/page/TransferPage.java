package page;
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
}
