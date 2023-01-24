package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileConfig;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

    public static URL getBrowserstackUrl() {
        try {
            return new URL(config.browserstackUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("browserstack.user", config.browserstackLogin());
        mutableCapabilities.setCapability("browserstack.key", config.browserstackPassword());

        mutableCapabilities.setCapability("app", config.app());

        mutableCapabilities.setCapability("device", "Google Pixel 3");
        mutableCapabilities.setCapability("os_version", "9.0");

        mutableCapabilities.setCapability("project", "Java Project");
        mutableCapabilities.setCapability("build", "browserstack-android-build");
        mutableCapabilities.setCapability("name", "selenide-android-test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}
