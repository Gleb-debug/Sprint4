package org.example.tests;

import org.example.pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;


public class AccordionTest {
    private MainPage mainPage;
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
    }

    @Test
    public void testAccordionQuestions() {
        mainPage.clickQuestion();
        String answer = mainPage.getAnswer();
        assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.", answer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
