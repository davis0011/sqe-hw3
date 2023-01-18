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
        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void goToLogin(){
        // locate and click on web element -> login
        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);
        // locate the password input box and enter password
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);
        // locate Log in button and press
        driver.findElement(By.id("loginbtn")).click();
    }

    public void enterForum(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"module-4\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/a"))).click();

    }
    public void addNewDiss(String sub){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div[2]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_subject"))).sendKeys(sub);

    }
    public void submitDiss(){
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




    public void checkForError() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"region-main\"]/div/div[1]/p[1]")));
    }


    public void searchStartDiss() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"permissionscapabilitysearch\"]"))).sendKeys("start");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"custom-select singleselect\" and @name=\"roleid\"]/option[3]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[td[label[input[@type=\"radio\" and @name=\"mod/forum:startdiscussion\" and @value=\"-1\"]]]]/td[3]/label/input"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"savechanges\" and @value=\"Save changes\"]"))).click();


    }
    public void searchStartDissAdmin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"permissionscapabilitysearch\"]"))).sendKeys("start");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"custom-select singleselect\" and @name=\"roleid\"]/option[6]"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[td[label[input[@type=\"radio\" and @name=\"mod/forum:startdiscussion\" and @value=\"-1\"]]]]/td[1]/label/input"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"savechanges\" and @value=\"Save changes\"]"))).click();


    }

    public void forumStuffOnly() throws Exception {
        List<WebElement> m = driver.findElements(By.xpath("/html/body/div[3]/div[4]/div/div[3]/div/section/div[2]/div[2]/table/tbody/tr[41]/td[2]/span[4]/a/i"));
        if(m.size()>0){
            throw new Exception();
        }
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









    public void terminateDriver(){
        // close all the driver windows
        // another option - to close a browser window: driver.close();
        driver.close();

    }





}
