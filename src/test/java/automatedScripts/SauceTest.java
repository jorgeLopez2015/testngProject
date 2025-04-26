package automatedScripts;

import static org.testng.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class SauceTest {

    WebDriver driver = null;
    WebDriverWait wait = null;


    @BeforeClass
    @Parameters({"sauceurl"})
    // Open the application Sauce Demo
    public void setUp(String sauceurl){
        try {
            //closeChangePasswordPopup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-web-security");
            options.addArguments("--no-proxy-server");

            Map prefs = new HashMap();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);


            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(sauceurl);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // Example of Implicit Wait
        }
        catch(Exception e){e.printStackTrace();}
    }


    @Test
    @Parameters({"username","pw", "clientFName", "clientLName", "zipCode"})
    public void validateSauceDemoApplication(String username , String pw, String clientFName, String clientLName, String zipCode){
        LoginSauceLab(username,pw);
        validateWelcomePageTitle();
        addingItemToCart();
        CheckingOutCart(clientFName, clientLName, zipCode);
        LogoutSauceLab();
    }

    // Login to SauceLab
    public void LoginSauceLab(String user , String pw){
        try{
            driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(user);
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pw);
            driver.findElement(By.xpath("//input[@id='login-button']")).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // Example of Implicit Wait
            //Thread.sleep(5000); // Example of Explicit Wait
        }
        catch(Exception e){e.printStackTrace();}
    }

    // Validate Welcome Page Title
    public void validateWelcomePageTitle(){
        try{
            //driver.switchTo().alert().accept();
            driver.findElement(By.xpath("//div[text()='Swag Labs']")).isDisplayed();
            Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Swag Labs']")).isDisplayed());
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void addingItemToCart(){
        try {

            driver.findElement(By.xpath(("//button[@id='add-to-cart-sauce-labs-backpack']"))).click();
            driver.findElement(By.xpath(("//button[@id='add-to-cart-sauce-labs-bike-light']"))).click();
            driver.findElement(By.xpath(("//button[@id='add-to-cart-sauce-labs-fleece-jacket']"))).click();
            String itemsAdded = driver.findElement(By.xpath(("//span[@class='shopping_cart_badge']"))).getText();
            if(itemsAdded.contains("3")){
                System.out.println("Items added correctly");
            }

        }
        catch (Exception e){e.printStackTrace();}
    }



    public void CheckingOutCart(String fname, String lname, String zip){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Example of Implicit Wait
        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        //Completing client info

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='first-name']")));
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys(fname);
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(lname);
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(zip);
        driver.findElement(By.xpath("//input[@id='continue']")).click();

        //Validating total
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='summary_total_label']")));
        String total = driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText();
        System.out.println("TOTAL VALUE: "+total);
        assertTrue(total.contains("$64.78"));
        //clicking on finish button
        driver.findElement(By.xpath("//button[@id='finish']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).isDisplayed());

    }

    // Logout from SauceLab
    public void LogoutSauceLab(){
        try{
            driver.findElement(By.xpath("//button[text()='Open Menu']")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Logout']")));
            driver.findElement(By.xpath("//a[text()='Logout']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//input[@id='login-button']")).isDisplayed());
        }
        catch(Exception e){e.printStackTrace();}
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
