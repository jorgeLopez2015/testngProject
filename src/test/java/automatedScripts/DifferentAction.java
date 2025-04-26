package automatedScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DifferentAction {

    WebDriver driver = null;

    @BeforeClass
    @Parameters({"doitUrl"})
    public void openApp(String doitUrl){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(doitUrl);
    }

    @Test
    public void doDifferentBrowserAction(){
        String title = driver.getTitle();
        String pageURL = driver.getCurrentUrl();
        System.out.println("The title is: "+title);
        System.out.println("The current url is: "+pageURL);
    }

    @Test
    @Parameters({"carDropdown"})
    public void doDifferentSeleniumAction(String carDropdown){
        validateRadioButton();
        validateCheckboxButton();
        readAnyPageText();
        selectDropdown(carDropdown);
    }


    public void  validateRadioButton(){

        boolean bmw = driver.findElement(By.xpath("//input[@value='bmw' and @type='radio']")).isSelected();
        boolean benz = driver.findElement(By.xpath("//input[@value='benz' and @type='radio']")).isSelected();
        boolean honda = driver.findElement(By.xpath("//input[@value='honda' and @type='radio']")).isSelected();

        System.out.println("Initially BMW radio button selection is ="+bmw);
        System.out.println("Initially BMW radio button selection is ="+benz);
        System.out.println("Initially BMW radio button selection is ="+honda);

        driver.findElement(By.xpath("//input[@value='honda' and @type='radio']")).click();
        honda = driver.findElement((By.xpath("//input[@value='honda' and @type='radio']"))).isSelected();
        System.out.println("Initially BMW radio button selection is ="+honda);
        //honda
    }

    public  void validateCheckboxButton(){
        boolean bmw = driver.findElement(By.xpath("//input[@value='bmw' and @type='checkbox']")).isSelected();
        boolean benz = driver.findElement(By.xpath("//input[@value='benz' and @type='checkbox']")).isSelected();
        boolean honda = driver.findElement(By.xpath("//input[@value='honda' and @type='checkbox']")).isSelected();
        System.out.println("Initially BMW radio button selection is ="+bmw);
        System.out.println("Initially BMW radio button selection is ="+benz);
        System.out.println("Initially BMW radio button selection is ="+honda);
        driver.findElement((By.xpath("//input[@value='honda' and @type='checkbox']"))).click();
        honda = driver.findElement(By.xpath("//input[@value='honda' and @type='checkbox']")).isSelected();
        System.out.println("Initially BMW radio button selection is ="+honda);
    }

    public void readAnyPageText(){
        String header = driver.findElement(By.xpath("//header[@id='top']/../h1")).getText();
        System.out.println("The Header text is: "+header);
    }

    public void selectDropdown(String car){
        Select select = new Select(driver.findElement(By.xpath("//select[@id='carselect']")));
        select.selectByVisibleText("Benz");
        System.out.println("The selected option is: "+car);
    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
