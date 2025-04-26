package automatedScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleTEst {

    @Test
    @Parameters({"googleUrl"})
    public void validateGoogleLogo(String googleUrl){

        WebDriver driver = new ChromeDriver();
        driver.get(googleUrl);
        driver.manage().window().maximize();
        if(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed()){
            System.out.println("Google logo is present");
        }

        driver.quit();

    }
}
