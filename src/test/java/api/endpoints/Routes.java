package api.endpoints;
import io.restassured.RestAssured;
/* 
Swagger URI --> https://petstore.swagger.io

*/
public class Routes 
{
public static String base_url="https://petstore.swagger.io/v2";

public  static void initialization()
{
	RestAssured.baseURI=Routes.base_url;
}

//User module

/******************** We can store all the user module data here in the store module ****************/

public static String post_url= "/user";
public static String get_url= "/user/{username}";
public static String update_url= "/user/{username}";
public static String delete_url= "user/{username}";

//Store module 

/******************** We can store all the store module data here in the store module ****************/

//Pet module 

/************ We can store all the pet module data here in the pet module ************/
}
