package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;

    public OrderTest(String name, String surname, String address, String station, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Пушкина, д. 10", "Бабушкинская", "+79991234567"},
                {"Петр", "Петров", "Москва, ул. Толстого, д. 5", "Сокольники", "+79876543210"}
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.firefox.driver", "src/test/resources/geckodriver.exe");
        driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
    }

    @Test
    public void testOrderFlowFromTopButton() {
        mainPage.clickOrderButtonTop();
        orderPage.fillFirstForm(name, surname, address, station, phone);
        orderPage.fillSecondForm(true, false, "Позвоните за час до доставки");
        boolean isOrderSuccessful = orderPage.isSuccessMessageDisplayed();
        Assert.assertTrue(isOrderSuccessful);
    }

    @Test
    public void testOrderFlowFromBotButton() {
        mainPage.clickOrderButtonBot();
        orderPage.fillFirstForm(name, surname, address, station, phone);
        orderPage.fillSecondForm(true, false, "Позвоните за час до доставки");
        boolean isOrderSuccessful = orderPage.isSuccessMessageDisplayed();
        Assert.assertTrue(isOrderSuccessful);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}