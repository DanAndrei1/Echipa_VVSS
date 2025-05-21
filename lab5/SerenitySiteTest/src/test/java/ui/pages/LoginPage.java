package ui.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;


@DefaultUrl("https://magento.softwaretestingboard.com/customer/account/login/")
public class LoginPage extends PageObject {

    @FindBy(linkText = "Sign In")
    WebElement signInLink;

    @FindBy(id = "email")
    WebElement emailField;

    @FindBy(id = "pass")
    WebElement passwordField;

    @FindBy(id = "send2")
    WebElement signInButton;

    @FindBy(css = ".message-error")
    WebElement errorMessage;

    public void openLoginForm() {
        open();
        signInLink.click();
    }

    public void loginAs(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
