Feature: Searching for votes
  Scenario: Votes features
    Given I have a base URL "https://api.thecatapi.com/v1" with header key "x-api-key" and header value "DEMO-API-KEY"
    When I send GET request with endpoint "/votes"
    Then I have to store responseBody to unmarshall
    And I should verify status code is 200
    Then I should verify the response result is more than 0

    When i select a random voteId from the stored votes
    Then I send GET request to "/votes/{id}" with random element
    Then I should verify status code is 200
    And the response should not be empty
    And the fields in the response should match the corresponding given object

    When I create a new vote using POST request to "/votes"
    Then the response status code should be 201
    And the response match expected value message "SUCCESS"
    And the id response is not empty

    When I send GET request with new id to "/votes/{id}"
    Then I should verify status code is 200
    And the id response should match id request

    When I send DELETE request with same id to "/votes/{id}"
    Then the response status code should be 200
    And the response body should match the expected value message "SUCCESS"

    When I send a GET request to "/votes/{id}"
    Then the response status code should be 404
    And the response should match the expected value messageBody "NOT_FOUND"




