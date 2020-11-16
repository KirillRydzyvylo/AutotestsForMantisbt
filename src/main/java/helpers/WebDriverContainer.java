package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelectorMode;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;

public class WebDriverContainer {
    private static WebDriver driver;
    /**
     * Возвращает статический экземпляр этого класса.
     *
     * @return экземпляр WebDriverContainer
     */
    public static synchronized WebDriverContainer getInstance() {
        return new WebDriverContainer();
    }

    /**
     * Возвращает экземпляр WebDriver (инициализирует его если он еще не инициализирован).
     *
     * @return экземпляр Selenium WebDriver
     */
    public WebDriver getWebDriver() {
        return WebDriverRunner.getWebDriver();
    }

    /**
     * Инициализирует статический экземпляр WebDriver.
     */
    public void setWebDriver() {
        this.setChromeDriver();
    }

    private void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", ConfigContainer.getInstance().getPathToTempFolderWithRandomName());
        ChromeOptions options = new ChromeOptions();
        options.setBinary(new File("src/test/resources/drivers/chromium/chrome.exe"));
        options.addArguments("--start-maximized");
        options.addArguments("--lang=ru");
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        Configuration.selectorMode = SelectorMode.Sizzle;
        Configuration.pollingInterval = 50;
        Configuration.timeout = 30000;
        Configuration.reportsFolder = ConfigContainer.getInstance().getConfigParameter("reportFilesPath");
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        WebDriverRunner.setWebDriver(driver);
    }
}
