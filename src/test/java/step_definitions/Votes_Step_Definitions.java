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
    public void setBaseUrlAndHeaders(String baseUrl, String headerKey, String headerValue) {
        votesController = new VotesController(baseUrl, headerKey, headerValue);
        
    }
    @When("I send GET request with endpoint {string}")
    public void sendGetRequest(String endpoint) {
        response = votesController.get(endpoint);
    }

    @Then("I have to store responseBody to unmarshall")
    public void storeResponseBodyToUnmarshall() {
        votes = response.getBody().as(Votes[].class);
    }

    @Then("I should verify status code is {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("I should verify the response result is more than {int}")
    public void verifyResponseResult(int zero) {
        int resultLength = votes.length;
        Assert.assertTrue(resultLength > zero);
    }

    @When("i select a random voteId from the stored votes")
    public void selectRandomVoteId() {
        Random random = new Random();
        int index = random.nextInt(votes.length);
        Votes selectedId = votes[index];
        randomId = String.valueOf(selectedId.getId());
    }

    @When("I send GET request to {string} with random element")
    public void sendGetRequestWithRandomVoteId(String endpoint) {
       String updatedEndpoint = endpoint.replace("{id}", randomId);
       response = votesController.get(updatedEndpoint);
    }
    
    @Then("the response should not be empty")
    public void verifyResponseNotEmpty() {
        Votes voteBody = response.getBody().as(Votes.class);
        Assert.assertNotNull(voteBody);
    }

    @Then("the fields in the response should match the corresponding given object")
    public void verifyResponseFieldsMatchGivenObject() {
        Votes expectedVote = Arrays.stream(votes).filter(v -> v.getId().equals(randomId))
                .findFirst()
                .orElse(null);
        Votes actualVote = response.getBody().as(Votes.class);
        Assert.assertEquals(expectedVote, actualVote);
    }
    
    @When("I create a new vote using POST request to {string}")
    public void createNewVote(String endpoint) throws JsonProcessingException {
        String voteJson = ObjectConverter.convertJavaToJson(EntitiesManager.createVote());
        response = votesController.post(endpoint, voteJson);
    }
    
    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("the response match expected value message {string}")
    public void verifyResponseMatchesExpectedValue(String expectedMessage) {
       newVote = response.getBody().as(Votes.class);
       String actualMessage = newVote.getMessage();
       Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Then("the id response is not empty")
    public void verifyIdResponseNotEmpty() {
       Assert.assertNotNull(newVote.getId());
    }

    @When("I send GET request with new id to {string}")
    public void sendGetRequestWithNewVoteId(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.get(updatedEndpoint);
    }
    
    @Then("the id response should match id request")
    public void verifyIdResponseMatchesRequest() {
       Votes responseBody = response.getBody().as(Votes.class);
       String actualId = responseBody.getId();
       String expectedId = newVote.getId();
       Assert.assertEquals(expectedId, actualId);
    }
    
    @When("I send DELETE request with same id to {string}")
    public void sendDeleteRequestWithNewVoteId(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.delete(updatedEndpoint);
    }
    
    @Then("the response body should match the expected value message {string}")
    public void verifyResponseBodyMatchesExpectedValue(String expectedMessage) {
        deletedVote = response.getBody().as(Votes.class);
        String actualMessage = deletedVote.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequestWithVoteId(String endpoint) {
        String updatedEndpoint = endpoint.replace("{id}", newVote.getId());
        response = votesController.get(updatedEndpoint);
    }

    @Then("the response should match the expected value messageBody {string}")
    public void verifyResponseMatchesExpectedValueNotFound(String expectedMessage) {
        String actualMessage = response.getBody().asString();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

}
