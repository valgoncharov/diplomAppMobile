package tests;

import com.codeborne.selenide.Configuration;
import config.MobileConfig;
import drivers.BrowserstackMobileDriver;
import drivers.LocalMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.Attach.getSessionId;

public class TestBase {
    static MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());

        switch (config.deviceHost()) {
            case "local":
                Configuration.browser = LocalMobileDriver.class.getName();
                break;
            case "remote":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
        Attach.attachAsText("Режим запуска: ", config.deviceHost());
        Attach.attachAsText("Устройство:", config.deviceName());
        Attach.attachAsText("Версия приложения:", config.app());
        Attach.attachAsText("Browserstack URL: ", config.browserstackUrl());
    }

    @AfterEach
    public void afterEach() {
        String sessionId = getSessionId();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();

        if (config.deviceHost().contains("remote")) {
            Attach.video(sessionId);
        }
    }
}