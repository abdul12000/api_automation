package Step_definitions;

import com.jayway.jsonpath.DocumentContext;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import utilities.RequestBodyServices;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class socialNetworkingStepDep extends BaseSteps implements En {
    Response responseForGetServiceUrl, responseForGetPostRequest, responseForPostCall, responseForGetCommentRequest, responseForPostCommentCall;
    RequestBodyServices requestBodyServices;
    DocumentContext requestBody;


    public socialNetworkingStepDep() {
        Given("^service is up and running$", () -> {
            setHeadersWithContentType();
            setEndpointPath(serviceUrl);
            getCall();
            responseForGetServiceUrl = getResponse();
            assertThat(responseForGetServiceUrl.statusCode(), is(200));


        });

        When("^i search for \"([^\"]*)\" of a post with a GET method$", (String id) -> {
            setHeadersWithContentType();
            setEndpointPath(makeAPostEndpoint + id);
            getCall();
            responseForGetPostRequest = getResponse();

        });

        Then("^i should get the correct \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" returned with status code of (\\d+)$",
                (Integer id, String title, String body, Integer sCode) -> {
                    assertThat(responseForGetPostRequest.statusCode(), is(sCode));
//          assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString().contains(id), is (true));
//                    assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString(), is (id));
                    assertThat(responseForGetPostRequest.body().jsonPath().get("id"), is(id));
                    assertThat(responseForGetPostRequest.body().jsonPath().get("title"), is(title));
                    assertThat(responseForGetPostRequest.body().jsonPath().get("body"), is(body));
                    assertNotNull(responseForGetPostRequest.body().jsonPath().get("userId"));
                });
        When("^I create a new post with the following details \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\",$", (String uId, String title, String body) -> {
            requestBodyServices = new RequestBodyServices();
            setHeadersWithContentType();
            requestBody = loadJsonTemplate(MakeAPostPayload);
            requestBodyServices.setRequestBodyForNewPost(requestBody, uId, title, body);
            setEndpointPath(makeAPostEndpoint);
            getPostCall();
            responseForPostCall = getResponse();

        });
        Then("^i should get the correct \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" returned with status code of (\\d+)$",
                (String uId, String title, String body, Integer sCode) -> {
                    assertThat(responseForPostCall.statusCode(), is(sCode));
                    assertThat(responseForPostCall.body().jsonPath().get("userId"), is(uId));
                    assertThat(responseForPostCall.body().jsonPath().get("title"), is(title));
                    assertThat(responseForPostCall.body().jsonPath().get("body"), is(body));
                    assertNotNull(responseForPostCall.body().jsonPath().get("id"));
                });
        When("^i search for comment with \"([^\"]*)\" with a GET method$", (String id) -> {
            setHeadersWithContentType();
            setEndpointPath(makeCommentEndpoint + id);
            getCall();
            responseForGetCommentRequest = getResponse();
        });
        Then("^i the correct \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\" are returned with status code of (\\d+)$",
                (Integer id, String name, String email, String body, Integer sCode) -> {
            assertThat(responseForGetCommentRequest.statusCode(), is(sCode));
//          assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString().contains(id), is (true));
//                    assertThat(responseForGetPostRequest.body().jsonPath().get("id").toString(), is (id));
            assertThat(responseForGetCommentRequest.body().jsonPath().get("id"), is(id));
            assertThat(responseForGetCommentRequest.body().jsonPath().get("name"), is(name));
            assertThat(responseForGetCommentRequest.body().jsonPath().get("email"), is(email));
                    assertThat(responseForGetCommentRequest.body().jsonPath().get("body"), is(body));
            assertNotNull(responseForGetCommentRequest.body().jsonPath().get("postId"));
        });
        When("^I create a new comment with the following details \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\",$",
                (String postId, String name, String email, String body) -> {
            requestBodyServices = new RequestBodyServices();
            setHeadersWithContentType();
            requestBody = loadJsonTemplate(MakeACommentPayload);
            requestBodyServices.setRequestBodyForNewComment(requestBody, postId, name, email, body);
            setEndpointPath(makeCommentEndpoint);
            getPostCall();
            responseForPostCommentCall = getResponse();

        });
        Then("^i should get the correct \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\", returned with status code of (\\d+)$",
                (String postId, String name, String email, String body, Integer sCode) -> {
            assertThat(responseForPostCommentCall.statusCode(), is(sCode));
            assertThat(responseForPostCommentCall.body().jsonPath().get("postId"), is(postId));
            assertThat(responseForPostCommentCall.body().jsonPath().get("name"), is(name));
                    assertThat(responseForPostCommentCall.body().jsonPath().get("email"), is(email));
            assertThat(responseForPostCommentCall.body().jsonPath().get("body"), is(body));
            assertNotNull(responseForPostCommentCall.body().jsonPath().get("id"));
        });
        Given("^service is up and running A$", () -> {
     System.out.println("test");

        });
        When("^We create a new comment with the following details \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\",$", (String arg0, String arg1, String arg2, String arg3) -> {
            System.out.println("test");

        });
        Then("^We should get the correct \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\", returned with status code of (\\d+)$", (String arg0, String arg1, String arg2, String arg3, Integer arg4) -> {
            System.out.println("test");
        });
    }


}
