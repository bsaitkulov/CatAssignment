package step_definitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.ObjectConverter;
import org.example.api.controller.VotesController;
import org.example.api.entities.Votes;
import org.example.api.utils.EntitiesManager;
import org.junit.Assert;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Bakai Saitkulov
 */

public class Votes_Step_Definitions{

   private Response response;
   private Votes[] votes;
    private   String randomId;
    private Votes newVote;
    private Votes deletedVote;
    private VotesController votesController;

    @Given("I have a base URL {string} with header key {string} and header value {string}")
    public void i_have_a_base_url_with_header_key_and_header_value(String baseUrl, String headerKey, String headerValue) {
        votesController = new VotesController(baseUrl, headerKey, headerValue);
        
    }
    @When("I send GET request with endpoint {string}")
    public void i_send_get_request_with_endpoint(String endpoint) {
        response = votesController.get(endpoint);
    }

    @Then("I have to store responseBody to unmarshall")
    public void i_have_to_store_response_body_to_unmarshall() {
        votes = response.getBody().as(Votes[].class);
    }

    @Then("I should verify status code is {int}")
    public void i_should_verify_status_code_is(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("I should verify the response result is more than {int}")
    public void i_should_verify_the_response_result_is_more_than(int zero) {
        int resultLength = votes.length;
        Assert.assertTrue(resultLength > zero);
    }

    @When("i select a random voteId from the stored votes")
    public void i_select_a_random_vote_id_from_the_stored_votes() {
        Random random = new Random();
        int index = random.nextInt(votes.length);
        Votes selectedId = votes[index];
        randomId = String.valueOf(selectedId.getId());
    }

    @When("I send GET request to {string} with random element")
    public void i_send_get_request_to_with_random_element(String endpoint) {
       String updatedEndpoint = endpoint.replace("{id}", randomId);
       response = votesController.get(updatedEndpoint);
    }
    
    @Then("the response should not be empty")
    public void the_response_should_not_be_empty() {
        Votes voteBody = response.getBody().as(Votes.class);
        Assert.assertNotNull(voteBody);
    }

    @Then("the fields in the response should match the corresponding given object")
    public void the_fields_in_the_response_should_match_the_corresponding_given_object() {
        Votes expectedVote = Arrays.stream(votes).filter(v -> v.getId().equals(randomId))
                .findFirst()
                .orElse(null);
        Votes actualVote = response.getBody().as(Votes.class);
        Assert.assertEquals(expectedVote, actualVote);
    }
    
    @When("I create a new vote using POST request to {string}")
    public void i_create_a_new_vote_using_post_request_to(String endpoint) throws JsonProcessingException {
        String voteJson = ObjectConverter.convertJavaToJson(EntitiesManager.createVote());
        response = votesController.post(endpoint, voteJson);
    }
    
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response match expected value message {string}")
    public void the_response_match_expected_value_message(String expectedMessage) {
       newVote = response.getBody().as(Votes.class);
       String actualMessage = newVote.getMessage();
       Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("the id response is not empty")
    public void the_response_is_not_empty() {
       Assert.assertNotNull(newVote.getId());
    }

    @When("I send GET request with new id to {string}")
    public void i_send_get_request_with_new_id_to(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.get(updatedEndpoint);
    }
    
    @Then("the id response should match id request")
    public void the_id_response_should_match_id_request() {
       Votes responseBody = response.getBody().as(Votes.class);
       String actualId = responseBody.getId();
       String expectedId = newVote.getId();
       Assert.assertEquals(expectedId, actualId);
    }
    
    @When("I send DELETE request with same id to {string}")
    public void i_send_delete_request_with_same_id_to(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.delete(updatedEndpoint);
    }
    
    @Then("the response body should match the expected value message {string}")
    public void the_response_body_should_match_the_expected_value_message(String expectedMessage) {
        deletedVote = response.getBody().as(Votes.class);
        String actualMessage = deletedVote.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.get(updatedEndpoint);
    }

    @Then("the response should match the expected value messageBody {string}")
    public void the_response_should_match_the_expected_value_message_body(String expectedMessage) {
        String actualMessage = response.getBody().asString();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

}
