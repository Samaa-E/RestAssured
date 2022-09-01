package Test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.Response;
public class TestRest {

    @Test (priority = 1)
    public void Get_all_Posts() {

        Response response= get("https://jsonplaceholder.typicode.com/posts");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
    }

    @Test (priority = 2)
    public void Get_post_by_Id() {

        Response response= get("https://jsonplaceholder.typicode.com/posts/1");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
    }


    @Test (priority = 3)
    public void Create_a_post() {


        String requestBody = "{\"title\": \"foo\",\"body\": \"bar\", \"userId\": \"1\"}";

        System.out.println(requestBody);

        given().
                header("Content-Type", "application/json").
                body(requestBody).
                when().
                    post("https://jsonplaceholder.typicode.com/posts").
                then().
                    assertThat().
                    statusCode(201).
                    assertThat().
                    body("title", equalTo("foo")).
                    body("body", equalTo("bar")).
                    body("userId", equalTo("1")).
                log().all();

    }
}