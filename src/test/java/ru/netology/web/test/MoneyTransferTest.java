package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setUpPage() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTransferFrom1To2() {

        var loginPage = new LoginPage();  // создаем объект LoginPage
        var authInfo = DataGenerator.getAuthInfo(); // переменная для хранения инфо авторизации
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCodeFor(authInfo); // ввод кода смс
        var dashboardPage = verificationPage.validVerify(verificationCode); //  нажимаем продолжить
        var firstCardBalance = dashboardPage.getFirstCardBalance(); //баланс 1 карты
        var secondCardBalance = dashboardPage.getSecondCardBalance(); // баланс 2 карты
        var money = 500;
        var chooseCard = dashboardPage.getSecondCardButton(); // куда переводим
        var cardFrom = DataGenerator.getFirstCard();
        var cardTo = DataGenerator.getSecondCard();
        var afterTransfer = chooseCard.transferMoney(money, cardFrom);
        var firstCardBalanceNew = afterTransfer.getFirstCardBalance();
        var secondCardBalanceNew = afterTransfer.getSecondCardBalance();

        int expectedFirst = firstCardBalance - money;
        int expectedSecond = secondCardBalance + money;
        int actualFirst = firstCardBalanceNew;
        int actualSecond = secondCardBalanceNew;

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);

    }

    @Test
    void shouldTransferFrom2To1() {

        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        var money = 1000;
        var chooseCard = dashboardPage.getFirstCardButton();
        var cardFrom = DataGenerator.getSecondCard();
        var cardTo = DataGenerator.getFirstCard();
        var afterTransfer = chooseCard.transferMoney(money, cardFrom);
        var firstCardBalanceNew = afterTransfer.getFirstCardBalance();
        var secondCardBalanceNew = afterTransfer.getSecondCardBalance();

        int expectedFirst = firstCardBalance + money;
        int expectedSecond = secondCardBalance - money;
        int actualFirst = firstCardBalanceNew;
        int actualSecond = secondCardBalanceNew;

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);
    }

    @Test
    void shouldTransferOverCardAmount() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardBalance = dashboardPage.getFirstCardBalance();
        var secondCardBalance = dashboardPage.getSecondCardBalance();
        var money = 50000;
        var chooseCard = dashboardPage.getFirstCardButton();
        var cardFrom = DataGenerator.getSecondCard();
        var cardTo = DataGenerator.getFirstCard();
        var afterTransfer = chooseCard.transferMoney(money, cardFrom);
        var firstCardBalanceNew = afterTransfer.getFirstCardBalance();
        var secondCardBalanceNew = afterTransfer.getSecondCardBalance();

        int expectedFirst = firstCardBalance + money;
        int expectedSecond = secondCardBalance - money;
        int actualFirst = firstCardBalanceNew;
        int actualSecond = secondCardBalanceNew;

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSecond, actualSecond);

    }
}

