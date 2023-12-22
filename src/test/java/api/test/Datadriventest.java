package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.Userendpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;


public class Datadriventest 
{
	Response response;
	User userpayload;
	
	
	@Test(dataProvider="Data",dataProviderClass=DataProviders.class)
	public void datasetup(String UserID,String UserName,String FirstName,String LastName,String email,String pwd,String phn)
	{
		userpayload=new User();
		userpayload.setId(Integer.parseInt(UserID));
		userpayload.setUsername(UserName);
		userpayload.setFirstName(FirstName);
		userpayload.setLastName(LastName);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phn);
	
		
	}
	@Test(priority=2,dependsOnMethods="datasetup")
	public void test_postuser()
	{
		response=Userendpoints.create_user(userpayload);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void test_deleteuser(String userName)
	{
		response=Userendpoints.delete_user(userName);
	    response.then().log().all(); 
	}
	
 
	
}
