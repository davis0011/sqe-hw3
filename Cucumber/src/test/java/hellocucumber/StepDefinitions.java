package hellocucumber;

import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    private static List<moodleSelenium> allMoodles;
    private static moodleSelenium moodle;
    private static int id = 1;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\david\\Desktop\\sqe-hw3\\Selenium\\chromedriver.exe";

    public void moodleInit() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if(allMoodles == null){
            allMoodles = new ArrayList<>();
        }
        moodle = new moodleSelenium();
        allMoodles.add(moodle);
        moodle.initSession(webDriver, path);
    }
    // $$*TODO* explain what this step does$$
    @Given("^User on course page Username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userOnCoursePage(String userName, String password) {
        moodleInit();
        moodle.goToLogin();
        moodle.enterLoginInfo(userName, password);
        moodle.myCoursesTab();
        moodle.goToCourse("course1");
    }

    @When("^User Navigate to Forum$")
    public void userNavigateToForum() {
        moodle.enterForum();
    }

    @And("^User start new discussion$")
    public void userStartNewDiscussion() {
        moodle.addNewDiss("test"+id);
        id+=1;
    }

    @Then("^Discussion is in forum$")
    public void discussionIsInForum() {
        id-=1;
        moodle.checkDiss("test" +id);
        id+=1;
    }
    // $$*TODO* explain what this step does$$

    @When("all step definitions are implemented")
    public void allStepDefinitionsAreImplemented() {
    }

    // $$*TODO* explain what this step does$$

    @Then("the scenario passes")
    public void theScenarioPasses() {
    }

    @And("^User navigate to forum premissions$")
    public void userNavigateToForumPremissions() {
        moodle.goToPrem();
    }

    @And("User delete student premission to start new discussion")
    public void userDeleteStudentPremissionToStartNewDiscussion() {
        moodle.searchStartDiss();
    }

    @Then("^Forum staff only$")
    public void forumStaffOnly() throws Exception {
        moodle.forumStuffOnly();
    }
}
