package pageObject;

import org.openqa.selenium.By;

public class DockerHome {

    public By headerMenuOption(String linkNane){
        String xpath = "(//a[text()='"+linkNane+"'])[1]";
        System.out.println(xpath);
        return By.xpath(xpath);
    }

    public By dockerLogo(){
        return By.xpath(("//li[@class='logo']/a"));
    }

    public By menuAlt(){
        return  By.xpath("//div[@class='menu-alt']/div/a");
    }

    public By menuLink(String position){
        return By.xpath("//div[@class='menu-alt']/div/a["+position+"]");
    }

    public By twitterLogo(){
        return By.xpath("//img[@alt='twitter logo']");
    }

    public By closeCookie(){
        return By.xpath("(//button[@aria-label='Close'])[1]");
    }

    public By linkNames(String i){
        return By.xpath("//a["+String.valueOf(i)+"]");
    }

    public By linkHrefs(int i){
        return By.xpath("//a["+String.valueOf(i)+"]");
    }


}
