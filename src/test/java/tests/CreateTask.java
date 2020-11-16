package tests;

import org.junit.jupiter.api.*;
import pages.bugs.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Тест для проверки возможноти создания задачи")
public class CreateTask extends Authentication {
    private LoginSelectProjectPag loginSelectProjectPag = new LoginSelectProjectPag();
    private LoginPasswordPage loginPasswordPage = new LoginPasswordPage();
    private BugReportPage bugReportPage = new BugReportPage();
    private MyViewPage myViewPage = new MyViewPage();
    private LoginPage loginPage = new LoginPage();
    private ViewPage viewPage = new ViewPage();

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
        loginPage.fillTheField("Пользователь", config.getConfigParameter("authenticateUserName"));
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
        loginPasswordPage.fillTheField("Пароль", config.getConfigParameter("authenticateUserPassword"));
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

    @Test
    @Order(9)
    @DisplayName("Нажимает на элемент 'Создать задачу' в левом меню")
    public void clickOnCreateTaskItem() throws Exception {
        myViewPage.clickOnMenuItem("Создать задачу");
        waitForPageLoad();
    }

    @Test
    @Order(10)
    @DisplayName("Нажимает на кнопку 'Выбрать проект'")
    public void clickOnSelectProjectButtonOnLoginSelectProjectPag() throws Exception {
        loginSelectProjectPag.clickOnButton("Выбрать проект");
        waitForPageLoad();
    }

    @Test
    @Order(11)
    @DisplayName("Заполняет выбираемые поля соответствующими значниями")
    public void fillSelectableFieldsOnBugReportPage() {
        bugReportPage.selectOptionValue("Категория", "api rest")
                .selectOptionValue("Воспроизводимость", "иногда")
                .selectOptionValue("Влияние", "малое")
                .selectOptionValue("Приоритет", "обычный")
                .selectOptionValue("Версия продукта", "2.0.0");
    }

    @Test
    @Order(12)
    @DisplayName("Заполняет поля для задачи")
    public void fillFieldOnBugReportPage() {
        String summary = "Тема " + config.getParameter("dateAndTimeStartTest");
        bugReportPage.fillTheField("Тема", summary)
                .fillTheField("Описание", "Немножко описания для данной задачи")
                .fillTheField("Шаги по воспроизведению", "Много шагов")
                .fillTheField("Дополнительные сведения", "Нет дополнительных сведений");
    }

    @Test
    @Order(13)
    @DisplayName("Нажимает на кнопку 'Создать задачу'")
    public void clickOnButtonBugReportPage() throws Exception {
        bugReportPage.clickOnButton("Создать задачу");
        waitForPageLoad();

    }

    @Test
    @Order(14)
    @DisplayName("Проверяет поля созданной заявки")
    public void checkFieldsOnViewPage() {

        viewPage.checkFieldValue("Категория", config.getParameter("Категория"))
                .checkFieldValue("Воспроизводимость", config.getParameter("Воспроизводимость"))
                .checkFieldValue("Влияние", config.getParameter("Влияние"))
                .checkFieldValue("Версия продукта", config.getParameter("Версия продукта"))
                .checkFieldValue("Тема", config.getParameter("Тема"))
                .checkFieldValue("Шаги по воспроизведению", config.getParameter("Шаги по воспроизведению"))
                .checkFieldValue("Дополнительные сведения", config.getParameter("Дополнительные сведения"));
    }


}
