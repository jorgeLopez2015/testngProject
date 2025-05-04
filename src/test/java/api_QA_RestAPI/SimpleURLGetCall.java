package api_QA_RestAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

public class SimpleURLGetCall {

    @Test
    public void doGoogleAPIAutomation(){

        try{
            RestAssured.baseURI = "http://www.google.com";
            Response res = RestAssured.get();

            int statusCode = res.getStatusCode();
            System.out.println("The status code: "+statusCode);
            Assert.assertEquals(statusCode, 200);
            System.out.println("The response string is: "+res.asString());
            Assert.assertTrue(res.asString().contains("<title>Google</title>"));
        }catch (Exception e){ e.printStackTrace();}

    }

    @Test
    public void doFacebookAPIAutomation(){

        try{
            RestAssured.baseURI = "https://www.facebook.com";
            Response res = RestAssured.get();

            int statusCode = res.getStatusCode();
            System.out.println("The status code: "+statusCode);
            Assert.assertEquals(statusCode, 200);
            System.out.println("The response string is: "+res.asString());
            Assert.assertTrue(res.asString().contains("Facebook"));
            System.out.println("The response is having test tote title");
        }catch (Exception e){ e.printStackTrace();}
    }

    @Test
    public void readingBook(){
        try{
            RestAssured.baseURI = "https://chercher.tech/sample/api/books.xml";
            Response res = RestAssured.get();
            int statusCode = res.getStatusCode();
            System.out.println("The status code: "+statusCode);
            Assert.assertEquals(statusCode, 200);
            String text = res.asString();
            //System.out.println("The response: "+text);
            XmlPath xml_path_obj = new XmlPath(text).using(xmlPathConfig().namespaceAware(false));
            String bookName = xml_path_obj.get("bookstore.book[0].title");
            System.out.println("The book name is: "+bookName);

        }catch (Exception e ){e.printStackTrace();}
    }

    @Test
    public void reqrestGet(){
        try{
            Response res = RestAssured.given().params("page", 2).when().get("https://reqres.in/api/users");
            int status_code = res.getStatusCode();
            System.out.println("Status code is: "+status_code);
            Assert.assertEquals(status_code, 200);
            String response = res.asString();
            JsonPath json_path_obj = new JsonPath(response).using(JsonPathConfig.jsonPathConfig());
            String first_name = json_path_obj.get("data[0].first_name");
            System.out.println("The first name is: "+first_name);
            Assert.assertEquals(first_name, "Michael");
        }catch (Exception e){e.printStackTrace();}
    }
}
