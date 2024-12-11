package com.epam.training.manish_kapoor.Task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PasteBinTest {
    private PasteBinHome pasteBinHome;
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        pasteBinHome = new PasteBinHome(driver);
    }

    @Test
    public void pasteBinTest() {
        pasteBinHome.openHomePage()
                .pasteContentInTextArea("Hello from WebDriver")
                .selectExpirationMenu()
                .chooseExpirationTimeTenMinutes()
                .enterPasteTitle("helloweb");

        assertEquals("helloweb", pasteBinHome.getTitleText());
        pasteBinHome.clickCreateButton();
    }

    @AfterEach
    public void teardown() {
        pasteBinHome.closeBrowser();
    }
}
