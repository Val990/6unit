package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataGenerator;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;


public class TransferPage {

    private SelenideElement sum = $("[data-test-id='amount'] input");  // cумма
    private SelenideElement from = $("[data-test-id='from'] input");// откуда
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']"); //пополнить


    public TransferPage() { //новый конструктор, обращается к полям
        sum.shouldBe(visible);
        from.shouldBe(visible);
        buttonTransfer.shouldBe(visible);
    }

    public DashboardPage transferMoney (int transfer, DataGenerator.CardData cardFrom) {
        sum.setValue(String.valueOf(transfer));
        from.setValue(cardFrom.getNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }
}
