package pageObject;

import org.openqa.selenium.By;

public class KubernetesHome {
    public By headerMenuOption(String linkNane){
        String xpath = "(//a[text()='"+linkNane+"'])[1]";
        System.out.println(xpath);
        return By.xpath(xpath);
    }
}
