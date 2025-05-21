package com.example.magento.steps;

import com.example.magento.pages.HomePage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeSteps {

    HomePage homePage;

    @Step
    public void openHomePage() {
        homePage.openUrl("https://magento.softwaretestingboard.com");
    }

    @Step
    public void verifyLogoIsVisible() {
        assertThat(homePage.isLogoVisible()).isTrue();
    }
}

