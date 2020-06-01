package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class TestBase {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // 1. Log in to https://ctco.lv/en page
        this.driver.get("https://ctco.lv/en");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void openCareersMenu() {
        driver.findElement(By.id("menu-item-127")).click();
    }

    public void clickVacancies() {
        waitForElementToBeVisible(By.cssSelector(".show-tablet .vacancies-item"), 2)
                .click();
    }

    public void openSpecificVacancy(String vacancyName) {
        waitForElementToBeVisible(By.className("table-display"), 2);
        List<WebElement> vacancies = driver.findElements(By.cssSelector("#menu-main-1 .menu-item a"));
        vacancies.stream()
                .filter(vacancy -> vacancy.getText().equals(vacancyName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Vacancy: " + vacancyName + " not found"))
                .click();
    }

    public int getSkillsQuantity() {
        waitForElementToBeVisible(By.className("vacancies-second-contents-wrap"), 4);
        WebElement vacancyText = driver
                .findElements(By.cssSelector(".vacancies-second-contents .text-block"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No vacancy text visible"));

        List<WebElement> allParagraphs = vacancyText.findElements(By.tagName("p"));

        for (int i = 0; i < allParagraphs.size(); i++) {
            if (allParagraphs.get(i).getText().equals("Professional skills and qualification:")) {
                WebElement skillsParagraph = allParagraphs.get(i + 1);
                return skillsParagraph.findElements(By.tagName("br")).size() + 1;
            }
        }
        return 0;
    }

    private WebElement waitForElementToBeVisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
