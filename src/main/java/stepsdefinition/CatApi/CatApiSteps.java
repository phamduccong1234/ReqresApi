package stepsdefinition.CatApi;

import static org.testng.Assert.assertEquals;

import java.io.File;
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
	String url, requestBodyFilePath;
	JsonUtils jsonUtils = new JsonUtils();
	ApiUtils apiUtils = new ApiUtils();
	HttpResponse<String> response;

	@Given("I have url and Method and request body of cat api")
	public void i_have_and_method_and_request_body_of_cat_api(DataTable catTable) {
		// Lay ten file request body va url
		List<Map<String, String>> list = catTable.asMaps(String.class, String.class);
		String requestBodyName = "";
		for (Map<String, String> m : list) {
			url = m.get("URL");
			requestBodyName = m.get("RequestBodyName");
		}

		requestBodyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\" + requestBodyName;

	}

//	@Given("I have {string} and Method and {string} of cat api")
//	public void i_have_and_method_and_of_cat_api(String givenUrl, String givenRequestBodyName) {
//		url = givenUrl;
//		String filePath = System.getProperty("user.dir") + "\\src\\main\\resources" + givenRequestBodyName;
//		JsonUtils jsonUtils = new JsonUtils();
//		requestBody = jsonUtils.readJsonFile(filePath);
//	}

	// For Successfully Case
	@When("I send cat request")
	public void i_send_cat_request() {
		String requestBody = jsonUtils.readJsonFile(requestBodyFilePath);
		response = apiUtils.sendPostRequest(url, requestBody);
	}

	@Then("I check {int} of cat api correctly")
	public void i_check_of_cat_api_correctly(int expectedStatusCode) {
		assertEquals(response.statusCode(), expectedStatusCode);
	}

	// For Validation Case
	@When("I send cat request with validation data with {string} and {string}")
	public void i_send_cat_request_with_validation_data(String fieldName, String value) {
		String requestBody = jsonUtils.createRequestBody(requestBodyFilePath, fieldName, value);
		response = apiUtils.sendPostRequest(url, requestBody);
		System.out.println("aaaa" + response.body().toString());
	}

	@Then("I check {int} and {string} of cat api correctly")
	public void i_check_expected_status_code_and_expected_message_of_cat_api_correctly(int expectedStatusCode,
			String expectedErrorMessage) {
		assertEquals(response.statusCode(), expectedStatusCode);
		System.out.println("Actual body: " + response.body());
		assertEquals(response.body().toString(), expectedErrorMessage);
	}

}
