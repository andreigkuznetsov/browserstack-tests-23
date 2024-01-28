package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:android.properties"})
public interface MobileConfig extends Config {

    @Key("device")
    @DefaultValue("Samsung Galaxy S22 Ultra")
    String getDevice();

    @Key("os_version")
    @DefaultValue("12.0")
    String getVersion();

    @Key("appUrl")
    String getApp();

}
