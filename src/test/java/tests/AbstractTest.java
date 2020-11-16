package tests;

import helpers.ConfigContainer;
import helpers.WebDriverContainer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractTest {

    protected ConfigContainer config = ConfigContainer.getInstance();

    public final Logger logger = LogManager.getLogger(getClass());

    /**
     * Код, который выполняется до каждого сценария.
     */
    @BeforeAll
    public void setUp() {
        Date dateAndTimeStartTest = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        config.setParameter("dateAndTimeStartTest", dateFormat.format(dateAndTimeStartTest));

        // Инициализируем статический экземпляр WebDriver
        WebDriverContainer.getInstance().setWebDriver();

        // Создаём папку для репортов
        File directory = new File(config.getConfigParameter("reportFilesPath"));
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Код, который выполняется после каждого сценария.
     */
    @AfterAll
    public void tearDown() {

        // Получаем текущий экземпляр WebDriver
        WebDriver driver = WebDriverContainer.getInstance().getWebDriver();

        // Закрываем браузер по завершению теста
        driver.quit();

    }
}

