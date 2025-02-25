package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    // Локаторы элементов
    private final By questionAccordion = By.xpath("//*[contains(@id,'accordion__heading')]");
    private final By answerAccordion = By.xpath("//*[contains(@id,'accordion__panel')]");
    private final By acceptCookie = By.id("rcc-confirm-button");
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonBottom = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAcceptCookie() {
        driver.findElement(acceptCookie).click();
    }
    public void clickQuestion(int index) {
        List<WebElement> questions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(questionAccordion));
        questions.get(index).click();
    }

    public String getAnswer(int index) {
        List<WebElement> answers = driver.findElements(answerAccordion);
        wait.until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).getText();
    }

    public void clickOrderButtonTop() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
    }

    public void clickOrderButtonBot() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
