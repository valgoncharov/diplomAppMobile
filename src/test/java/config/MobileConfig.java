package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/${deviceHost}.properties",
        "classpath:config/credentials.properties"
})
public interface MobileConfig extends Config {
    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("browserstackUrl")
    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String browserstackUrl();

    @Key("app")
    @DefaultValue("bs://b3329f44da140f8046974f5ecdba6c1d950fb9e9")
    String app();

    @Key("deviceHost")
    @DefaultValue("remote")
    String deviceHost();
    String browserstackLogin();
    String browserstackPassword();
}