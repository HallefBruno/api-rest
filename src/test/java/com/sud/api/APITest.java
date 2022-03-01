
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
	  .log().all()
      .when()
        .get("/todo")
      .then()
        .statusCode(200)
		.log().all();
  }
  
  @Test
  public void deveSalvarTarefa() {
    RestAssured.given()
	  .log().all()	
      .when()
        .body("{\"task\" : \"Comprar novos cursos\", \"dueDate\" : \"2022-02-27\"}")
        .contentType(ContentType.JSON)
        .post("/todo")
      .then()
        .statusCode(201)
		.log().all();
  }
  
  @Test
  public void naoDeveSalvarTarefaInvalida() {
    RestAssured.given()
	  .log().all()
      .when()
        .body("{\"task\" : \"Comprar novos cursos\", \"dueDate\" : \"2010-02-27\"}")
        .contentType(ContentType.JSON)
        .post("/todo")
      .then()
        .statusCode(400)
        .body("error", CoreMatchers.is("Bad Request"))
		.log().all();
  }
}