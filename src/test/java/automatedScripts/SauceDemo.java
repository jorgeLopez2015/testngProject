package automatedScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceDemo {

    WebDriver driver = null;

    @BeforeClass
    @Parameters({"sauceUrl"})
    public void openApp(String sauceUrl){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(sauceUrl);
    }

    @Test
    @Parameters({"username", "passwordUser", "invalidPassword"})
    public void loginPageTest(String user, String password, String invalidPassword){
        invalidLogin(user, invalidPassword);
        validLogin(user, password);
    }

    public void validLogin(String user, String password){

        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(user);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        String header = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        if(header.contentEquals("Swag Labs")){
            System.out.println("Login successfully done");
        }
        driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.findElement(By.xpath(("//button[@id='react-logout_sidebar_link']"))).click();

    }

    public void invalidLogin(String user, String invaludUser){
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(user);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(invaludUser);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        if(driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed()){
            System.out.println("Login failed");
        }
        driver.findElement(By.xpath("//input[@id='user-name']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.findElement(By.xpath("//input[@id='password']")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
}
