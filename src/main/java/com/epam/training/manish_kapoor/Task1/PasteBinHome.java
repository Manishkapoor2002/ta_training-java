package com.epam.training.manish_kapoor.Task1;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasteBinHome {
    //    pastebin Home page URL:
    private static final String URL = "https://pastebin.com/";
    // Field
    WebDriver webDriver;
    WebDriverWait wait;
    //Locators
    @FindBy(xpath = "//textarea[@id = 'postform-text']")
    private WebElement pasteTextArea;

    @FindBy(xpath = "//*[@id = \"select2-postform-expiration-container\"]")
    private WebElement pasteExpirationMenuButton;

    @FindBy(xpath = "//li[contains(text(), '10 Minutes')]")
    private WebElement pasteExpirationTenMinutes;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement titleInputBox;


    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;

    //    constructor
    public PasteBinHome(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    // Method for open Home page(pastebin.com) and selecting textarea
    public PasteBinHome openHomePage() {
        webDriver.get(URL);
        try {
            wait.until(ExpectedConditions.visibilityOf(pasteTextArea));
        } catch (TimeoutException err) {
            throw new TimeoutException("It take more time to load, thus the text area is not visible");
        }
        return this;
    }

    //Method for pasting the content in the textarea:
    public PasteBinHome pasteContentInTextArea(String content) {
        pasteTextArea.sendKeys(content);
        return this;
    }

    //select the expiration menu button
    public PasteBinHome selectExpirationMenu() {
        pasteExpirationMenuButton.click();
        return this;
    }

    //    choose the ten-minute option from the menu
    public PasteBinHome chooseExpirationTimeTenMinutes() {
        wait.until(ExpectedConditions.visibilityOf(pasteExpirationTenMinutes)).click();
        return this;
    }

    //set the title of the paste
    public PasteBinHome enterPasteTitle(String Title) {
        titleInputBox.sendKeys(Title);
        return this;
    }

    public PasteBinHome clickCreateButton() {
        createNewPasteButton.click();
        return this;
    }

    public void closeBrowser() {
        webDriver.quit();
    }

    public String getTitleText() {
        return titleInputBox.getAttribute("value");
    }


}
