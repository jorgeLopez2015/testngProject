package understandingGrouping;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testNGGrouping {

    @BeforeClass
    public void setUp(){
        System.out.println("Initiating Chrome");
    }

    @Test(groups = {"e2e"})
    public void testCaseOne(){
        System.out.println("This is test flow test001");
    }

    @Test(groups = {"smoke", "regression"})
    public void testCaseTwo(){
        System.out.println("This is test flow test002");
    }

    @Test(groups = {"smoke", "anotherGroup"})
    public void testCaseThree(){
        System.out.println("This is test flow test003");
    }

    @Test(groups = {"regression"})
    public void testCaseFour(){
        System.out.println("This is test flow test004");
    }

    @Test(groups = {"smoke"})
    public void testCaseFive(){
        System.out.println("This is test flow test005");
    }

    @AfterClass
    public void tearDown(){
        System.out.println("This method closes driver");
    }
}
