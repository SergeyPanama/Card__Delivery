package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

class DeliveryTest {

    @BeforeAll
    static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpEach() {
        open("http://localhost:7777");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        val validUser = DataGenerator.Registration.generateUser("ru");
        int daysToAddForFirstMeeting = 4;
        int daysToAddForSecondMeeting = 7;
        $("[data-test-id='city'] .input__control").setValue(validUser.getCity());
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        DataGenerator.generateDate(daysToAddForFirstMeeting)));
        $("[data-test-id='name'] .input__control").setValue(validUser.getName());
        $("[data-test-id='phone'] .input__control").setValue(validUser.getPhone());
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Запланировать")).click();
        $(withText("Встреча успешно запланирована"))
                .shouldBe(Condition.text(DataGenerator.generateDate(daysToAddForFirstMeeting)));
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        DataGenerator.generateDate(daysToAddForSecondMeeting)));
        $$("button").find(Condition.exactText("Запланировать")).click();
        $(withText("Перепланировать?"))
                .shouldBe(Condition.visible, Duration.ofSeconds(3));
        $$("button").find(Condition.exactText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована"))
                .shouldBe(Condition.text(DataGenerator.generateDate(daysToAddForSecondMeeting)));
    }

}