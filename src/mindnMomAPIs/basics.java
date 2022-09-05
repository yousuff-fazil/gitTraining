package mindnMomAPIs;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;


public class basics {
	int spotlights;
	static String token;
	static SessionFilter session = new SessionFilter();
	static String spotLightData;
	static String spotId;
	public static String pointTitle;
	static JsonPath js3;
	public static String buttonName;
	static List pointerHead;
	static List pointerContent;
	static List faqList;
	
	public static int noOfSpotlight() {
		

		RestAssured.baseURI = "http://3.6.37.251:5001/";
		
		String auth = given().log().all().header("Content-Type", "application/json")
				.body("{\n" + "\"email_or_number\": \"mdyousuf.fazil@gmail.com\",\n"
						+ "\"password\": \"Test@123\"\n"+ "}")
				.when().post("/user/login").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
				
		
		
		JsonPath js2 = new JsonPath(auth);
		
		token = js2.getString("data.accessToken"); 
		
		System.out.println("address " + token);
		
		String spotlight = given().log().all().queryParam("target_city","chennai")
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.header("authorization", token)
				.filter(session)
				.when().get("/user/spolight").then().log().all()
				.extract().response().asString();
		
		JsonPath js7 = new JsonPath(spotlight);
		
		String spotId3 = js7.getString("data.listing._id");
		
		spotId  = spotId3.replaceAll("\\[", "").replaceAll("\\]","");
		System.out.println("spot id here " + spotId);
		int spotlights = js7.getInt("data.listing.size()"); 
		
		return spotlights;
	}

	//
	
	public static String spotLightName() {
		spotLightData = given().log().all().queryParam("spotlight_id",spotId)
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.header("authorization", token)
				.filter(session)
				.when().get("/user/spolightbyid").then().log().all()
				.extract().response().asString();
		js3 = new JsonPath(spotLightData);
		
		buttonName = js3.getString("data.listing.cta_button_name").replaceAll("\\[", "").replaceAll("\\]","");
		String mybtn = js3.getString("data.listing.cta_button_name");
		System.out.println("mybtn" + mybtn);
		pointTitle = js3.getString("data.listing.pointer_title").replaceAll("\\[", "").replaceAll("\\]","");
		System.out.println("title point" + pointTitle);
		String name = js3.getString("data.listing.product_name").replaceAll("\\[", "").replaceAll("\\]","");
		System.out.println("prod name " + name);
		return name;
	}
	
	public static String spotlightTitle() {
		String title = js3.getString("data.listing.title").replaceAll("\\[", "").replaceAll("\\]","");
		System.out.println("btn " + buttonName);
		return title;
	}
	
	public static List<String> pointHead(){
		pointerHead = js3.getList("data.listing.pointers[0].pointer_heading");
		System.out.println(" header size " + pointerHead.size());
		System.out.println(" header value " + pointerHead.get(0));
		return pointerHead;
		
	}
	
	public static List<String> pointContent(){
		pointerContent = js3.getList("data.listing.pointers[0].pointer_content");
		System.out.println(" header size " + pointerContent.size());
		System.out.println(" header value " + pointerContent.get(0));
		return pointerContent;
		
	}
	
	public static List<String> faqList(){
		faqList = js3.getList("data.listing.faq[0].your_question");
		System.out.println(" header size " + faqList.size());
		System.out.println(" header value " + faqList.get(0));
		return faqList;
		
	}
	
	
	public static void main(String[] args) {
		

		RestAssured.baseURI = "http://3.6.37.251:5001/";
		
		String auth = given().log().all().header("Content-Type", "application/json")
				.body("{\n" + "\"email_or_number\": \"mdyousuf.fazil@gmail.com\",\n"
						+ "\"password\": \"Test@123\"\n"+ "}")
				.when().post("/user/login").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();
				
		
		
		JsonPath js2 = new JsonPath(auth);
		
		token = js2.getString("data.accessToken"); 
		
		System.out.println("address " + token);
		
		String spotlights = given().log().all().queryParam("target_city","chennai")
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.header("authorization", token)
				.filter(session)
				.when().get("/user/spolight").then().log().all()
				.extract().response().asString();
		
		JsonPath js4 = new JsonPath(spotlights);
		
		int spotlightValue = js4.getInt("data.listing.size()"); 
		
		
		System.out.println("number of spotlight " + noOfSpotlight());
		int i=0;
		String desc = js4.getString("data.listing.carousel[0].description[0]"); 
		System.out.println("desc of spotlight " + desc);
		
		noOfSpotlight();
		spotLightName();
		pointHead();
	}

}