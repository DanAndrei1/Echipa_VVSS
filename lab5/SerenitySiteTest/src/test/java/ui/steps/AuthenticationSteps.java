package ui.steps;

import net.thucydides.core.annotations.Step;
import ui.pages.LoginPage;

public class AuthenticationSteps {

    LoginPage loginPage;

    @Step("Log in with valid credentials")
    public void logIn(String user, String pass) {
        loginPage.openLoginForm();
        loginPage.loginAs(user, pass);
    }

    @Step("Log out")
    public void logOut() {
        loginPage.$( "a[aria-label='Account']" ).click();
        loginPage.$( "a[title='Sign Out']" ).click();
    }
}
