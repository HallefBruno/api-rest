
package com.sud.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;


public class APITest {
  
  @BeforeClass
  public static void setUp() {
    RestAssured.baseURI = "http://127.0.0.1:8001/tasks-backend";
  }
  
  @Test
  public void deveRetornarTarefas() {
    RestAssured.given()
      .when()
        .get("/todo")
      .then()
        .statusCode(200);
  }
  
  @Test
  public void deveSalvarTarefa() {
    RestAssured.given()
      .when()
        .body("{\"task\" : \"Comprar novos cursos\", \"dueDate\" : \"2022-02-27\"}")
        .contentType(ContentType.JSON)
        .post("/todo")
      .then()
        .statusCode(201);
  }
  
  @Test
  public void naoDeveSalvarTarefaInvalida() {
    RestAssured.given()
      .when()
        .body("{\"task\" : \"Comprar novos cursos\", \"dueDate\" : \"2010-02-27\"}")
        .contentType(ContentType.JSON)
        .post("/todo")
      .then()
        .statusCode(400)
        .body("error", CoreMatchers.is("Bad Request"));
  }
}