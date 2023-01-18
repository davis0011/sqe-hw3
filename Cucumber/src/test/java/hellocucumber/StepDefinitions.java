package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StepDefinitions {
    private static List<moodleSelenium> allMoodles;
    private static moodleSelenium moodleS;
    private static moodleSelenium moodleT;
    private static moodleSelenium moodleA;
    private static int id = 1;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\david\\Desktop\\sqe-hw3\\Selenium\\chromedriver.exe";


    /**
     * Basic init method to create multiple moodle selenium drivers dependent on string input
     **/
    public void moodleInit(String user) {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if(allMoodles == null){
            allMoodles = new ArrayList<>();
        }
        switch (user){
            case("teacher"):
                moodleT = new moodleSelenium();
                allMoodles.add(moodleT);
                moodleT.initSession(webDriver, path);
            case("student"):
                moodleS = new moodleSelenium();
                allMoodles.add(moodleS);
                moodleS.initSession(webDriver, path);
            default:
                moodleA = new moodleSelenium();
                allMoodles.add(moodleA);
                moodleA.initSession(webDriver,path);
        }
    }

    /**
     * Initializes the user and navigates from moodle landing page to the course that contains the forum to be tested.
     * Params are user to identify if teacher or student so that we can combine several methods into singles. see reuses.
     * Username and Password are for moodle login
     **/
    @Given("^User named \"([^\"]*)\" on course page Username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void userOnCoursePage(String user,String userName, String password) {
        moodleInit(user);
        moodleSelenium moodle;
        if (user.equals("teacher")){moodle = moodleT;}
        else{moodle = moodleS;}
        moodle.goToLogin();
        moodle.enterLoginInfo(userName, password);
        moodle.myCoursesTab();
        moodle.goToCourse("course1");
    }

    /**
     * User navigates to forum. Both users perform this action, so it takes user as input.
     * **/
    @When("^User named \"([^\"]*)\" Navigate to Forum$")
    public void userNavigateToForum(String user) {
        moodleSelenium moodle;
        if (user.equals("teacher")){moodle = moodleT;}
        else{moodle = moodleS;}
        moodle.enterForum();
    }

    /**
     * Student only method to start creating a new discussion in the forum we are testing.
     * */
    @And("^User start creation for new discussion$")
    public void userStartNewDiscussion() {
        moodleS.addNewDiss("test"+id);
    }

    /**
     * Student only method to finish creating a new discussion in the forum we are testing. This step leads to an
     * alternate destination if the forum was set to "staff only" before it is called.
     * */
    @And("^User finish creation for new discussion$")
    public void userSubmitDiscussion() {
        moodleS.submitDiss();
        id+=1;
    }

    /**
     * Student only method to verify that the discussion was created in the forum
     * */
    @Then("^Discussion is in forum$")
    public void discussionIsInForum() {
        id-=1;
        moodleS.checkDiss("test" +id);
        id+=1;
//        moodleS.terminateDriver();
    }

    /**
     * Teacher only method to navigate to forum permissions
     * */
    @And("^User navigate to forum permissions$")
    public void userNavigateToForumpermissions$() {
        moodleT.goToPrem();
    }

    /**
     * Teacher only method to remove forum permissions from students
     * */
    @And("User delete student permission to start new discussion")
    public void userDeleteStudentPermissionToStartNewDiscussion() {
        moodleT.searchStartDiss();
    }

    /**
     * Teacher only method to check forum permissions from students is removed
     * */
    @Then("^Forum staff only$")
    public void forumStaffOnly() throws Exception {
         moodleT.forumStuffOnly();
//         moodleT.terminateDriver();
    }

    /**
     * Student only method to make sure the discussion was not added to the forum if the permission were removed
     * */
    @And("^Discussion not in forum$")
    public void discussionNotInForum(){
        moodleS.checkForError();
    }

    /**
     * Admin only method to navigate from landing screen to forum permissions
     * */
    @Given("^Admin navigate to premissions$")
    public void adminNavigateToPremissions() {
//        if(!forumLocked) return;
        moodleInit("admin");
        moodleA.goToLogin();
        moodleA.enterLoginInfo("admin","!Admin123");
        moodleA.myCoursesTab();
        moodleA.goToCourse("course1");
        moodleA.enterForum();
        moodleA.goToPrem();
    }

    /**
     * Admin only method to set student forum permissions to allowed
     * */
    @And("^Allow student$")
    public void allowStudent() {
        moodleA.searchStartDissAdmin();
    }

    /**
     * yay
     * */
    @Then("^exit$")
    public void exit() {
        System.out.println("yay");
    }
}
