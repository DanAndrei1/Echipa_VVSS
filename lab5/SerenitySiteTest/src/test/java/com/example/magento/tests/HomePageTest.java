package com.example.magento.tests;

import com.example.magento.steps.HomeSteps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class HomePageTest {

    @Steps
    HomeSteps steps;

    @Test
    public void should_display_homepage_logo() {
        steps.openHomePage();
        steps.verifyLogoIsVisible();
    }
}
