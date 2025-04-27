package kubernetesTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.KubernetesHome;

import java.time.Duration;

public class KubernetesAction {

    WebDriver driver = null;
    KubernetesHome KH;

    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    @BeforeClass
    @Parameters({"kubernetesUrl"})
    public void setUp(String kubernetesUrl){
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") +"/KubernetesTest.html");
        extent.attachReporter(spark);
        extent.setSystemInfo("Host Name", "Kubernetes - Home Page");
        spark.config().setDocumentTitle("Kubernetes Test");
        spark.config().setReportName("Testing the Kubernetes page");
        spark.config().setTheme(Theme.STANDARD);
        logger = extent.createTest("Testing the Kubernetes app using Selenium TestNG");
        KH = new KubernetesHome();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(kubernetesUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("The User is launching the Kubernetes site");
    }

    @Test
    public void differentActions(){

    }

    public void hoverHeaderMenu(){
        try {
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
            new Actions(driver).moveToElement(driver.findElement(KH.headerMenuOption("Documenation")));
            Thread.sleep(2000);
            logger.pass("Documentation menu successfully displayed");
        }catch (Exception e){e.printStackTrace();}
    }




}
