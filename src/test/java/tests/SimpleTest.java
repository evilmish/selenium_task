package tests;

import org.testng.Assert;
import org.testng.annotations.Test;


public class SimpleTest extends TestBase {

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
