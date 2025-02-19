package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderPage {
    private final By nameField = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath("//div[@class='Dropdown-root']");
    private final By rentalPeriodOption = By.xpath("//div[contains(text(),'сутки')]");
    private final By blackCheckbox = By.id("black");
    private final By greyCheckbox = By.id("grey");
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmOrderButton = By.xpath("//button[text()='Да']");
    private final By successMessage = By.xpath("//div[text()='Заказ оформлен']");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillFirstForm(String name, String surname, String address, String station, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).sendKeys(station);
        driver.findElement(metroStationField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(metroStationField).sendKeys(Keys.ENTER);
        driver.findElement(phoneField).sendKeys(phone);
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void fillSecondForm(boolean isBlack, boolean isGrey, String comment) {
        driver.findElement(deliveryDateField).sendKeys(Keys.ENTER);
        driver.findElement(rentalPeriodField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentalPeriodOption)).click();

        if (isBlack) {
            driver.findElement(blackCheckbox).click();
        }
        if (isGrey) {
            driver.findElement(greyCheckbox).click();
        }

        driver.findElement(commentField).sendKeys(comment);
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();
    }

    public boolean isSuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
}