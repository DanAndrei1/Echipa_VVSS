package ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://magento.softwaretestingboard.com/checkout/")
public class CheckoutPage extends PageObject {

    @FindBy(name = "firstname")    WebElement firstName;
    @FindBy(name = "lastname")     WebElement lastName;
    @FindBy(name = "street[0]")    WebElement street;
    @FindBy(name = "city")         WebElement city;
    @FindBy(name = "postcode")     WebElement zip;
    @FindBy(name = "telephone")    WebElement phone;
    @FindBy(name = "region_id")    WebElement state;
    @FindBy(css  = "button.action.continue.primary") WebElement nextToPayment;

    private final By checkMoneyOrder = By.cssSelector("input[value='checkmo']");
    @FindBy(css  = "button.action.primary.checkout") WebElement placeOrderButton;

    @FindBy(css = ".checkout-success .base") WebElement successHeader;

    public void fillShippingAddressIfNeeded()
    {
        firstName.sendKeys("Demo");
        lastName.sendKeys("User");
        street.sendKeys("Strada Teodor Mihali 55");
        city.sendKeys("Cluj-Napoca");
        state.sendKeys("Cluj");
        zip.sendKeys("400000");
        phone.sendKeys("0741234567");
        nextToPayment.click();
    }

    public void selectCheckMoneyOrder() {
        find(checkMoneyOrder).click();
    }

    public void placeOrder() {
        nextToPayment.click();
    }

    public String successMessage() {
        return successHeader.getText();
    }
}
