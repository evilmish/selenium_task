package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SimpleTest extends TestBase {

    @FindBy(css = "#menu-item-127")
    WebElement careersMenuItem;

    @Test
    public void professionalSkillsVerificationTest() {

        // 2. Open Careers menu
        openCareersMenu();

        // 3. Click Vacancies from the list.
        clickVacancies();

        // 4. Open vacancy with title "Test Automation Engineer"
        openSpecificVacancy("Test Automation Engineer");

        // Verify that paragraph under Professional skills and qualification:
        // contains exactly 5 skills.
        int skills = getSkillsQuantity();
        Assert.assertEquals(skills, 5, "Actual amount of skills is wrong");

    }

}
