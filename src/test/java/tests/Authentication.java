package tests;

import org.junit.jupiter.api.*;
import pages.bugs.LoginPage;
import pages.bugs.LoginPasswordPage;
import pages.bugs.MyViewPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тест для проверки возможноти аутентификации пользователем")
public class Authentication extends CommonTest {
    protected MyViewPage myViewPage = new MyViewPage();
    private LoginPage loginPage = new LoginPage();
    private LoginPasswordPage loginPasswordPage = new LoginPasswordPage();

    @Test
    @Order(0)
    @DisplayName("Шаг подготовки тестовых данных")
    public void prepareStep() {
    }

    @Test
    @Order(1)
    @DisplayName("Открываем главную страницу сайта")
    public void userOpensPage() throws Exception {
        userOpensMainPage();
        waitForPageLoad();
    }

    @Test
    @Order(2)
    @DisplayName("Нажимаем на кнопку 'Вход'")
    public void clickOnRegistrationButtonOnMyViewPage() throws Exception {
        myViewPage.clickOnButton("Вход");
        waitForPageLoad();
    }

    @Test
    @Order(3)
    @DisplayName("Заполняет поле 'Пользователь'")
    public void fillTheFieldUserOnLoginPage() {
        loginPage.fillTheField("Пользователь",config.getConfigParameter("authenticateUserName"));
    }

    @Test
    @Order(4)
    @DisplayName("Нажимаем на кнопку 'Вход'")
    public void clickOnEnterButtonOnLoginPage() throws Exception {
        loginPage.clickOnButton("Вход");
        waitForPageLoad();
    }

    @Test
    @Order(5)
    @DisplayName("Заполняет поле 'Пароль'")
    public void fillTheFieldPasswordOnLoginPasswordPage() {
        loginPasswordPage.fillTheField("Пароль",config.getConfigParameter("authenticateUserPassword"));
    }

    @Test
    @Order(6)
    @DisplayName("Сбрасывает флажок 'Доступ к этой сессии будет только с ...'")
    public void resetCheckBoxSecureSessionOnLoginPasswordPage() throws Exception {
        loginPasswordPage.resetCheckBox("Доступ к этой сессии будет только с ...");
    }

    @Test
    @Order(7)
    @DisplayName("Нажимаем на кнопку 'Вход'")
    public void clickOnEnterButtonOnLoginPasswordPage() throws Exception {
        loginPasswordPage.clickOnButton("Вход");
        waitForPageLoad();
    }

    @Test
    @Order(8)
    @DisplayName("Проверяется имя пользователя'")
    public void checkUserNameOnOnMyViewPage() throws Exception {
        myViewPage.checkUserName(config.getConfigParameter("authenticateUserName"));
        waitForPageLoad();
    }
}
