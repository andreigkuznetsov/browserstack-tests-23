package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:authentication.properties"})
public interface AuthenticationConfig extends Config {

    @Key("username")
    String getUserLogin();

    @Key("password")
    String getUserPassword();

    @Key("remoteUrl")
    String getRemoteUrl();
}
