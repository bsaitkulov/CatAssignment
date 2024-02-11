package org.example.api.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import static io.restassured.RestAssured.given;


@Slf4j
public class VotesController {

    private String baseUrl;
    private String headerKey;
    private String headerValue;
    Response response;

    private RequestSpecification spec;

    public VotesController(String baseUrl, String headerKey, String headerValue) {
        this.baseUrl = baseUrl;
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        this.spec = given()
                .baseUri(baseUrl)
                .header(headerKey, headerValue);
    }

    public Response get(String endpoint){
        log.info("Performed GET: {}", endpoint);
        return given()
                .spec(this.spec)
                .get(endpoint);
    }

    public Response post(String endpoint, String body){
        log.info("Performed POST: {}", endpoint);
        log.info("Body is: {}", body);
        return given()
                .spec(this.spec)
                .contentType(ContentType.JSON)
                .body(body)
                .post(endpoint);
    }

    public Response delete(String endpoint){
        log.info("Performed DELETE: {}", endpoint);
        return given()
                .spec(this.spec)
                .delete(endpoint);
    }




    public static void main(String[] args) {

    }



}
