package helpers;

import config.AuthenticationConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    public static String videoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        AuthenticationConfig authConfig = ConfigFactory.create(AuthenticationConfig.class, System.getProperties());

        return given()
                .log().all()
                .auth().basic(authConfig.getUserLogin(), authConfig.getUserPassword())
                .when()
                .get(url)
                .then()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
