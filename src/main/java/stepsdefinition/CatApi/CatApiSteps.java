package stepsdefinition.CatApi;

import static org.testng.Assert.assertEquals;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import common.ApiUtils;
import common.JsonUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CatApiSteps {
	String url, requestBody;
	HttpResponse<String> response;
	@Given("I have {string} and Method and {string} of cat api")
	public void i_have_and_method_and_of_cat_api(String givenUrl, String givenRequestBodyName) {
	    url = givenUrl;
	    String filePath = System.getProperty("user.dir") + "\\src\\main\\resources" + givenRequestBodyName;
	    JsonUtils jsonUtils = new JsonUtils();
	    requestBody = jsonUtils.readJsonFile(filePath);
	}

	@When("I send cat request")
	public void i_send_cat_request() {
	    ApiUtils apiUtils = new ApiUtils();
	    response = apiUtils.sendPostRequest(url, requestBody);
	}

	@Then("I check {int} of cat api correctly")
	public void i_check_of_cat_api_correctly(int expectedStatusCode) {
	    assertEquals(response.statusCode(), expectedStatusCode);
	}
	
	@Given("I have url and Method and request body of cat api with {string} and {string}")
	public void i_have_and_method_and_of_cat_api_with_image_id_and_null(String fieldName, String value, DataTable catTable) {
		// Lay ten file request body va url
	    List<Map<String, String>> list = catTable.asMaps(String.class, String.class);
	    String requestBodyName = "";
	    for(Map<String, String> m : list) {
	    	url = m.get("URL");
	    	requestBodyName = m.get("RequestBodyName");
	    }
	    
	    // Copy 1 file json goc -> file moi
	    
	    // Sua data tren file moi
	    
	    // Lay request body theo file moi
	    
	}

	@Then("I check <ExpectedStatusCode> and <ExpectedMessage> of cat api correctly")
	public void i_check_expected_status_code_and_expected_message_of_cat_api_correctly() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
