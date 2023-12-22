package api.endpoints;

//User endpoints.java file used for creating CRUD operations 
//Create for performing  Create,read,update,delete requests the user API

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Userendpoints   
{
	 public static  Response response;
 public static Response create_user(User Payload)
 {
	 Routes.initialization();
	 
   response=given()
   .contentType(ContentType.JSON)
   .accept(ContentType.JSON)
   .body(Payload)
   .when()
   .post(Routes.post_url);
   
   return response;
   
 }
 public static Response read_user(String Username)
 {
	  response=given()
	 .pathParam("username", Username)
	 .when()
	 .get(Routes.get_url);
	 return response;
	 
 }

 public static Response update_user(String Username, User Payload)
 {
	 
   Response response=given()
   .contentType(ContentType.JSON)
   .accept(ContentType.JSON)
   .pathParam("username", Username)
   .body(Payload)
   
   .when()
   .put(Routes.update_url);
   
   return response;
   
 }
 public static Response delete_user(String Username)
 {
	  response=given()
	.pathParam("username", Username)
	.when()
	.delete(Routes.delete_url);
	 return response;
 }

}
