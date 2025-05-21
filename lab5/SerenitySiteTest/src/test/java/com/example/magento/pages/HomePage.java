package com.example.magento.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

@DefaultUrl("https://magento.softwaretestingboard.com/")
public class HomePage extends PageObject {

    @FindBy(css = ".logo")
    WebElement logo;

    public boolean isLogoVisible() {
        return logo.isDisplayed();
    }
}
