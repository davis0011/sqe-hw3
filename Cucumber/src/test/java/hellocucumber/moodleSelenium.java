package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
public class moodleSelenium {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));


        // launch website -> localhost
        driver.get("http://localhost/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void goToLogin(){
        // locate and click on web element -> login
        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        // $x("//*[@id='username']")
        // driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);

        // locate the password input box and enter password
        // $x("//*[@name='password' and @type='password']")
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.id("loginbtn")).click();
    }

    public void enterForum(){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'test forum')]"))).click();
        WebElement forum = driver.findElement(By.xpath("//*[@href=\"http://localhost/mod/forum/view.php?id=4\"]"));
        forum.click();
    }
    public void addNewDiss(String sub){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div[2]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_subject"))).sendKeys(sub);
        driver.findElement(By.id("id_messageeditable")).sendKeys("test");
        driver.findElement(By.id("id_submitbutton")).click();
    }
    public void checkDiss(String s) {
        driver.findElement(By.xpath("//*[@title='test1']"));
    }

    public void goToPrem() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[6]/a"))).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[6]/ul/li[2]")).click();
    }

    public void teacherWelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, Teacher!')]")
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, Teacher!')]"));
    }

    public void loginSequence(String username, String password){
        // locate and click on web element -> login
        goToLogin();

        // enter username and password -> press login
        enterLoginInfo(username, password);

        // check for welcome message
        teacherWelcomeMessage();
    }

    public void logout(){
        // find user menu -> click on it
        driver.findElement(By.id("user-menu-toggle")).click();

        // find log out button in drop down menu -> click on it
        driver.findElement(By.linkText("Log out")).click();

    }

    public void checkNotLoggedIn(){
        // to make sure we logged out -> find the login button again
        // $x("//*[contains(text(),'Log in')][1]")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Log in')][1]")));

    }

    public void checkNotLoggedInAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        System.out.println("num of windows" + driver.getWindowHandles().size());
        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);

            checkNotLoggedIn();
        }
    }

    public void logoutAndCheck(){
        logout();
        checkNotLoggedIn();
    }

    public void searchStartDiss() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"permissionscapabilitysearch\"]"))).sendKeys("start");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[4]/div/div[3]/div/section/div[2]/div[2]/table/tbody/tr[41]/td[2]/span[1]/a/i"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[5]/div[2]/div/div/div[3]/button[2]"))).click();

    }

    public void forumStuffOnly() throws Exception {
        List<WebElement> m= driver.findElements(By.xpath("/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/div[2]/table/tbody/tr[41]/td[2]/span[4]/a/i"));
        if(m.size()>0){
            throw new Exception();
        }
    }

    public void editModeOn(){
        // find edit mode switch -> click on it
        // $x("//*[@type='checkbox' and @name='setmode']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }
    public void myCoursesTab(){
        // example of how we can save the element and then operate with it
        // find my courses -> click on it
        // $x("//*[contains(text(),'My courses') and @role='menuitem']")
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }

    public void goToCourse(String courseName){
        // find course -> click on it
        // $x("//*[@class='multiline' and contains(text(),'Demo course')]")[0].click()
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }

    // add quiz from course page


    public void logoutAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);
            logout();
        }
    }







    public void terminateDriver(){
        // close all the driver windows
        // another option - to close a browser window: driver.close();
        driver.quit();

    }

    // submits quiz and exits quiz review and goes back to course page




}
