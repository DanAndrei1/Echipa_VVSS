package ui.tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.steps.AuthenticationSteps;
import ui.steps.ShoppingSteps;

@ExtendWith(SerenityJUnit5Extension.class)
public class PlaceOrderTest {

    /* Replace with valid demo credentials */
    private static final String USER = "dan@joicdw.com";
    private static final String PASS = "Test@123";

    /* A product slug that exists in the demo store */
    private static final String PRODUCT_SLUG = "breathe-easy-tank";  // results in /radiant-tee.html

    @Steps AuthenticationSteps auth;
    @Steps ShoppingSteps       shop;

    @Test
    void user_can_place_order_end_to_end() {
        auth.logIn(USER, PASS);
        shop.addProductToCart(PRODUCT_SLUG);
        shop.checkoutWithCheckMoneyOrder();
        shop.shouldSeeSuccessMessage();
        //auth.logOut();
    }
}
