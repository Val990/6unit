package ru.netology.web.data;

import lombok.Value;

public class DataGenerator {
    private DataGenerator() {}

    @Value
    public static class AuthInfo { // 1 - ввод логина и пароля
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode { // 2 - ввод кода смс
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardData {  // 3 - карты
        private String number;
    }

    public static CardData getFirstCard() {
        return new CardData("5559 0000 0000 0001");
    }

    public static CardData getSecondCard() {
        return new CardData("5559 0000 0000 0002");
    }

}
