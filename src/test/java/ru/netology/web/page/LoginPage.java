package ru.netology.web.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

public class LoginPage {

    private SelenideElement login = $("[data-test-id='login'] input"); //логин
    private SelenideElement password = $("[data-test-id='password'] input"); //пароль
    private SelenideElement loginButton = $("[data-test-id='action-login']"); // кнопка продолжить

    public LoginPage() { //новый конструктор, обращается к полям
        loginButton.shouldBe(visible);
        password.shouldBe(visible);
        loginButton.shouldBe(visible);
    }

    public VerificationPage validLogin(DataGenerator.AuthInfo info) { //принимает инфо из data generator
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage(); // т к после клика попадаем на новую стр верификации
    }
}
