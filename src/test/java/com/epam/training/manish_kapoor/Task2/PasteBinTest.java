package com.epam.training.manish_kapoor.Task2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PasteBinTest {
    private final String CODE_SNIPPET = "            git config --global user.name  \"New Sheriff in Town\"\n" +
            "            git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "            git push origin master --force";

    private final String TITLE = "how to gain dominance among developers";

    private static PasteBinHome homePage;
    private static PasteBin pasteBinPage;
    private final String HIGHLIGHTING_OPTION = "Bash";

    @BeforeAll
    public static void setup() {
        homePage = new PasteBinHome(new ChromeDriver());
    }

    @AfterAll
    public static void tearDown() {
        if (homePage != null) {
            homePage.closeBrowser();
        }
        if (pasteBinPage != null) {
            pasteBinPage.closeBrowser();
        }
    }

    @Test
    @Order(1)
    public void homePageTest() {
        homePage.openHomePage()
                .pasteContentInTextArea(CODE_SNIPPET)
                .selectExpirationMenu()
                .chooseExpirationTimeTenMinutes()
                .clickSyntaxHighlightingMenu()
                .chooseSyntaxHighlightBash()
                .enterPasteTitle(TITLE);
        assertEquals(CODE_SNIPPET, homePage.getCodeText());
        assertEquals(TITLE, homePage.getTitleText());

        pasteBinPage = homePage.clickCreateButton();
    }

    @Test
    @Order(2)
    public void testCodeSnippetMatch() {
        assertEquals(CODE_SNIPPET, pasteBinPage.getTextFromTextArea());
    }

    @Test
    @Order(3)
    public void testTitleMatch() {
        assertEquals(TITLE, pasteBinPage.getPasteTitle());
    }

    @Test
    @Order(4)
    public void testHighlightingOptionMatch() {
        assertEquals(HIGHLIGHTING_OPTION, pasteBinPage.getHighLightingOption());
    }
}
