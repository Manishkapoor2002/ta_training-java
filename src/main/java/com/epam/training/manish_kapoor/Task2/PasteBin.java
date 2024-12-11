package com.epam.training.manish_kapoor.Task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasteBin {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private String pasteBinPageTitle;

    @FindBy(xpath = "//a[contains(@class, 'btn') and contains(@class, '-small')]")
    private WebElement syntaxHighlightingOption;
    @FindBy(xpath = "//div[@class='info-top']//h1")
    private WebElement pasteTitle;
    @FindBy(xpath = "//ol[@class='bash']")
    private WebElement textArea;

    public PasteBin(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
        pasteBinPageTitle = webDriver.getTitle();
    }

    public String getTextFromTextArea() {
        return textArea.getText();
    }

    public String getPasteTitle() {
        return removeSuffixFromTitle();
    }

    public void closeBrowser() {
        webDriver.quit();
        webDriver = null;
    }

    public String getHighLightingOption() {
        return syntaxHighlightingOption.getText();
    }

    private String removeSuffixFromTitle() {
        String suffix = " - Pastebin.com";
        return pasteBinPageTitle.endsWith(suffix) ? pasteBinPageTitle.substring(0, pasteBinPageTitle.length() - suffix.length()) : pasteBinPageTitle;
    }


}
