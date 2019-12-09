package utilities;

import Step_definitions.BaseSteps;
import com.jayway.jsonpath.DocumentContext;

public class RequestBodyServices  extends BaseSteps{
    public void setRequestBodyForNewPost(DocumentContext requestBody, String uId, String title, String body){
        requestBody.set("userId", uId);
        requestBody.set("title", title);
        requestBody.set("body", body);
    }
    public void setRequestBodyForNewComment(DocumentContext requestBody, String postId, String name, String email, String body){
        requestBody.set("postId", postId);
        requestBody.set("name", name);
        requestBody.set("email", email);
        requestBody.set("body", body);
    }
}
