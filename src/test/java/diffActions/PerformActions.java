package diffActions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.DockerHome;
import pageObject.WorldMetersHome;

import javax.swing.*;

public class PerformActions {

    WebDriver driver = null;
    DockerHome DH;
    WorldMetersHome WMH;

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeClass
    @Parameters({"dockerUrl"})
    public void setUp(String dockerUrl){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") +"/PencilSelenium.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Docker Application - Home Page");


        DH = new DockerHome();
        WMH = new WorldMetersHome();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(dockerUrl);
    }

    @Test
    public void doDifferentBrowserAction(){
        /*String title = driver.getTitle();
        String pageURL = driver.getCurrentUrl();
        System.out.println("The title is: "+title);
        System.out.println("The current url is: "+pageURL);
        doMouseHover();
        validLogo();
        validateMenuAltSection();*/
        //Worldmeter
        //gettingCountValues();
        printAllLinks();
        scrollingToElement();
        handleTwitterWindow();
    }

    public void gettingCountValues(){
        String value = driver.findElement(WMH.deathValue("India")).getText();
        System.out.println("THe India death: "+value);
    }

    public void scrollingToElement(){
        try {
            WebElement twitterLogo = driver.findElement(DH.twitterLogo());
            new Actions(driver).scrollToElement(twitterLogo).perform();
            Thread.sleep(2000);
            if(driver.findElement(DH.closeCookie()).isDisplayed()){
                driver.findElement(DH.closeCookie()).click();
                Thread.sleep(3000);
            }
            twitterLogo.click();

        }catch (Exception e){e.printStackTrace();}
    }

    public void handleTwitterWindow(){
        try {
            Object[] windowHandles = driver.getWindowHandles().toArray();
            driver.switchTo().window((String) windowHandles[1]);
            String twitterURL = driver.getCurrentUrl();
            System.out.println("URL: "+twitterURL);
            Assert.assertTrue(twitterURL.contains("x.com/docker"));
            driver.close();

        }catch (Exception e){e.printStackTrace();}
    }

    public void printAllLinks(){
        try {
            int totalLinks = driver.findElements(By.xpath("//a")).size();
            for(int i=1; i<=totalLinks;i++){
                String linkName = driver.findElement(By.xpath("//a["+String.valueOf(i)+"]")).getText();
                String linkHref = driver.findElement(By.xpath("//a["+String.valueOf(i)+"]")).getDomAttribute("href");
                System.out.println("The link position: "+i+" "+"Link name "+linkName+" and href is"+linkHref);
            }


        }catch (Exception e){e.printStackTrace();}
    }

    public void doMouseHover(){
        try{
            /*WebElement products = driver.findElement(By.xpath("(//a[text()='Products'])[1]"));
            WebElement developers = driver.findElement(By.xpath("(//a[text()='Developers'])[1]"));
            WebElement pricing = driver.findElement(By.xpath("(//a[text()='Pricing'])[1]"));
            WebElement support = driver.findElement(By.xpath("(//a[text()='Support'])[1]"));
            WebElement company = driver.findElement(By.xpath("(//a[text()='Company'])[1]"));*/

            //WebElement hoverable = driver.findElement(By.xpath(""));
            new Actions(driver).moveToElement(driver.findElement(DH.headerMenuOption("Products"))).perform();
            Thread.sleep(2000);
            new Actions(driver).moveToElement(driver.findElement(DH.headerMenuOption("Products"))).perform();
            Thread.sleep(2000);
            /*new Actions(driver).moveToElement(pricing).perform();
            Thread.sleep(2000);
            new Actions(driver).moveToElement(support).perform();
            Thread.sleep(2000);
            new Actions(driver).moveToElement(company).perform();
            Thread.sleep(2000);*/
        }catch(Exception e){e.printStackTrace();}

    }

    public void validateMenuAltSection(){
        try {

            int menuAltLinks = driver.findElements(DH.menuAlt()).size();
            System.out.println("Total links present: "+menuAltLinks);
            for(int a=1; a<=menuAltLinks; a++){
                System.out.println("The link name is: "+driver.findElement(DH.menuLink(String.valueOf(a))).getText());
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void validLogo(){
        Assert.assertTrue(driver.findElement(DH.dockerLogo()).isDisplayed());
        System.out.println("The logo is displayed");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
