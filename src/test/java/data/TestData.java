package data;

import com.github.javafaker.Faker;

public class TestData {

    private final Faker faker = new Faker();

    public String enSearchWord = faker.options().option("BrowserStack", "Appium", "Java"),
            ruSearchWord = faker.options().option("Кремль", "Москва", "Россия"),
            russian = "Russian",
            language = "RU",
            errorMessage = "An error occurred";

    public int stringNumber = 0;
}
