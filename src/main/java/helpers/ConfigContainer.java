package helpers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigContainer {
    public static final Logger logger = LogManager.getLogger(ConfigContainer.class);

    private static final String PROPERTIES_FILE_NAME = "src/test/resources/config/config.properties";

    // Статический экземпляр этого класса (собственно сам ConfigContainer)
    private static volatile ConfigContainer instance;

    // Настройки тестовой среды (считываются из файла config.properties и используются во всех тестовых сценариях)
    private Properties properties = new Properties();

    //Параметры для данного теста
    private Map<String, String> parameters = new HashMap<>();

    /**
     * Методы доступа к экземпляру этого класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) {
            instance = new ConfigContainer();
            instance.loadConfig();
        }
        return instance;
    }

    // region Относительный путь к файлу с настройками тестовой среды
    private String getPropertiesFileName() {
        return PROPERTIES_FILE_NAME;
    }

    /**
     * Загружает настройки тестовой среды из файла [config.properties].
     */
    public void loadConfig() {
        InputStream input = null;
        try {
            input = new FileInputStream(getPropertiesFileName());
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Возвращает значение их файла конфигураций.
     */
    public String getConfigParameter(String key) {
        return properties.getProperty(key);
    }

    public void setParameter(String key, String value) {
        logger.info("Установлен ключ: [" + key + "] и параметр: [" + value + "]");

        // Блокируем возможность записать в параметр значения null или "пустая строка"
        Assert.assertTrue("Попытка установить значение ключа равно NULL",
                key != null);
        Assert.assertTrue("Попытка установить значение параметра = null",
                value != null);
        Assert.assertFalse("Попытка установить пустое значение ключа",
                key.equals(""));
        Assert.assertFalse("Попытка установить пустое значение параметра",
                value.equals(""));

        // Защита от "дурака" - перезапись существующего параметра в большинстве случаев признак ошибки в коде
        if (parameters.get(key) != null) {
            logger.info("  [ВНИМАНИЕ]: перезапись значения параметра, старое значение: " +
                    "[" + parameters.get(key) + "], новое значение: [" + value + "]");
        }

        parameters.put(key, value);
    }

    public String getParameter(String key) {
        // Контролируем переданное значение ключа для поиска параметра (не null и не пустая строка)
        Assert.assertTrue("Значение переданного ключа = null !", key != null);
        Assert.assertFalse("Пустое значение переданного ключа !", key.equals(""));

        String value = parameters.get(key);

        // Контролируем полученное по ключу значение параметра (не null и не пустая строка)
        logger.info("Получен ключ: <" + key + "> и параметр: <" + value + ">");
        Assert.assertTrue("Значение полученного параметра равно NULL", value != null);
        Assert.assertFalse(" Пустое значение полученного параметра", value.equals(""));

        return value;
    }

    /**
     * Получить путь к временной папке загруженного файла со случайным именем
     */
    public String getPathToTempFolderWithRandomName() {
        return getPathToTempFolder() + "\\temp" + getParameter("dateAndTimeStartTest");
    }

    /**
     * Получить путь к временной папке со всеми загруженными файлами
     */
    public String getPathToTempFolder() {
        return System.getProperty("user.dir") + properties.getProperty("tempFolder");
    }

    /**
     * Получить главную страницу
     */
    public String getSiteUrl() {
        return properties.getProperty("OpenSiteUrl");
    }
}
