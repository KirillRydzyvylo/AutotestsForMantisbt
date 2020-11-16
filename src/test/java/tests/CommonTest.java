package tests;

import helpers.WebDriverContainer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class CommonTest extends AbstractTest {

    /*******************************************************************************************************************
     * Методы страницы.
     ******************************************************************************************************************/
    /**
     * Пользователь открывает главную страницу
     */
    public void userOpensMainPage() {
        logger.info("[ИНФОРМАЦИЯ] открываем главную страницу");

        String urlLocal = config.getSiteUrl();
        open(urlLocal);

        logger.info("Произведено открытие страницы: " + urlLocal);
    }

    /**
     * Ожидает полной загрузки страницы.
     */
    public void waitForPageLoad() throws Exception {
        WebDriver driver = WebDriverContainer.getInstance().getWebDriver();
        TimeUnit.SECONDS.sleep(1);
        new WebDriverWait(driver, 90).until((ExpectedCondition<Boolean>) wd ->
                (Boolean) ((JavascriptExecutor) wd).executeScript("return document.readyState == 'complete'"));
        this.waitForAjax(driver); // Ждем завершения Ajax на странице (именно в таком порядке: колесо -> Ajax)
    }

    /**
     * Ожидает завершения Ajax на странице.
     */
    protected void waitForAjax(WebDriver driver) throws Exception {
        Boolean ajaxIsComplete;
        int i = 0;         // Счетчик попыток
        int nTries = 100;  // Количество попыток
        do {
            i++;
            Thread.sleep(100);
            ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).
                    executeScript("return window.jQuery != undefined && window.jQuery.active == 0");
        } while (!ajaxIsComplete && i < nTries);  // Цикл пока не закончатся все активные jQuery
    }
}
