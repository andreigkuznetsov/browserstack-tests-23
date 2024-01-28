package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AuthenticationConfig;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider{

    static MobileConfig testConfig = ConfigFactory.create(MobileConfig.class, System.getProperties());
    static AuthenticationConfig authConfig = ConfigFactory.create(AuthenticationConfig.class, System.getProperties());

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", authConfig.getUserLogin());
        mutableCapabilities.setCapability("browserstack.key", authConfig.getUserPassword());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", testConfig.getApp());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", testConfig.getDevice());
        mutableCapabilities.setCapability("os_version", testConfig.getVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL(authConfig.getRemoteUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
