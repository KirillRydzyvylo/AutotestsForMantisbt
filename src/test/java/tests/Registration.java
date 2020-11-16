package tests;

import org.junit.jupiter.api.*;
import pages.bugs.MyViewPage;
import pages.bugs.SignUpPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тест для проверки возможноти регистрации пользователя")
public class Registration extends CommonTest {
    private MyViewPage myViewPage = new MyViewPage();
    private SignUpPage signUpPage = new SignUpPage();


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
    @DisplayName("Нажимаем на кнопку 'Зарегистрировать новую учётную запись'")
    public void clickOnRegistrationButtonOnMyViewPage() throws Exception {
        myViewPage.clickOnButton("Зарегистрировать новую учётную запись");
        waitForPageLoad();
    }

    @Test
    @Order(3)
    @DisplayName("Заполняет поле 'Пользователь'")
    public void fillUserNameField() {
        String userName = config.getConfigParameter("registrationUserName") +
                config.getParameter("dateAndTimeStartTest");
        config.setParameter("userName", userName);
        signUpPage.fillTheField("Пользователь", userName);
        signUpPage.checkTheFieldValue("Пользователь", userName);
    }

    @Test
    @Order(4)
    @DisplayName("Заполняет поле 'Пользователь'")
    public void fillEmailField() {
        String email = config.getConfigParameter("registrationEmail")
                .replace("@", config.getParameter("dateAndTimeStartTest") + "@");
        config.setParameter("email", email);
        signUpPage.fillTheField("Электронная почта", email);
        signUpPage.checkTheFieldValue("Электронная почта", email);
    }

    @Test
    @Order(5)
    @DisplayName("Заполняет поле 'Пользователь'")
    public void fillCaptchaField() {
        String captcha = "{someMusterKey}";//
        signUpPage.fillTheField("Капча", captcha);
        signUpPage.checkTheFieldValue("Капча", captcha);
    }
}
