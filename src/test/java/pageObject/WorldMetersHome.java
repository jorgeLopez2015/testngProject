package pageObject;

import org.openqa.selenium.By;

public class WorldMetersHome {

    /*public By headerMenuOption(String linkNane){
        String xpath = "(//a[text()='"+linkNane+"'])[1]";
        System.out.println(xpath);
        return By.xpath(xpath);
    }*/

    public By deathValue(String country){
        String xpath ="//table[@id='main_table_countries_today']//tbody[1]/tr/td/a[text()='"+country+"']/../../td[4]";
        return  By.xpath(xpath);
    }
}
