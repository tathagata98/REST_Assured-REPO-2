package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Userendpoints;
import api.payload.User;
import io.restassured.response.Response;

public class usertest 
{
	Faker faker;
	User userpayload;
	Response response;
	public Logger logger;
	
	@BeforeClass
	
public void setup()
{
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setPassword(faker.internet().password());
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
}
	@Test(priority=1)
	public void testpostuser()
	{
		logger.info("**************** Creating user *************************");
		
		response=Userendpoints.create_user(userpayload);
	    response.then().log().all();
	    
	    Assert.assertEquals(response.getStatusCode(), 200);
	
	    logger.info("**************** User is created  *************************");
	}
 
	@Test(priority=2)
	public void testgetuserbyname()
	{
		logger.info("**************** Reading user info *************************");
		response=Userendpoints.read_user(this.userpayload.getUsername());
		response.then().log().all();

	    Assert.assertEquals(response.getStatusCode(), 200);
	
	    logger.info("**************** User info is displayed *************************");
	}
	@Test(priority=3)
	public void updateuserbyname()
	{
		logger.info("**************** Updating user *************************");
		//Updating the values
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setUserStatus(1);
		
		response=Userendpoints.update_user(this.userpayload.getUsername(), userpayload);
	    response.then().log().all();
	    Assert.assertEquals(response.getStatusCode(), 200);
	    
	    //Checking the updated value
	    testgetuserbyname();
	    
	    logger.info("**************** User is updated *************************");
	}
	@Test(priority=4)
	public void deleteuserbyname()
	{
		
		logger.info("**************** Deleting user *************************");
		
		response=Userendpoints.delete_user(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	    
		logger.info("**************** User is deleted *************************");
	}
}
