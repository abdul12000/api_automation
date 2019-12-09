package restAssuredBDD;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class RestBDD {
    @Test
    public void runRestBDD() {

        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("userId", "10299");
        postContent.put("title", "this is my title");
        postContent.put("body", "This is finally my body");


        given().contentType(ContentType.JSON).
                with().body(postContent).
                when().post("https://jsonplaceholder.typicode.com/posts/")
                .then().body("title", is ("this is my title"));
    }
}
