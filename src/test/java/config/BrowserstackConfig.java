package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${environment}.properties")
public interface BrowserstackConfig extends Config{

    @Key("user")
    @DefaultValue("bsuser_FnXAfW")
    String user();
    @Key("key")
    @DefaultValue("pwS5QQ24JpmVQbqp8mVY")
    String key();
    @Key("app")
    @DefaultValue("bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c")
    String app();
    @Key("url")
    @DefaultValue("http://hub.browserstack.com/wd/hub")
    String url();
}
