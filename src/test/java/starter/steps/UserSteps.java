package starter.steps;

import com.google.gson.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import starter.apis.UserAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserSteps {

	private final UserAPI userAPI = new UserAPI();
	private Map<String, String> userDataToUpdate;
	int userId;
	Response userResponse, userUpdateResponse;


	@Given("I search for a user with ID {int}")
	public void iSearchForUserWithId(int userId) {
		this.userId = userId;
		// No action needed in Given step for this scenario
	}

	@When("I call the user API endpoint")
	public void iCallTheUserApiEndpoint() {
		userAPI.searchUser(userId);
		userResponse = SerenityRest.lastResponse();
	}

	@Then("the response status code is {int}")
	public void theResponseStatusCodeIs200(int statusCode) {
		restAssuredThat(userResponse -> userResponse.statusCode(statusCode));

	}

	@And("the response body contains the user data:")
	public void theResponseBodyContainsTheUserData(DataTable dataTable) {
		List<Map<String, String>> expectedData = dataTable.asMaps();

		restAssuredThat(userResponse -> {
			for (Map<String, String> expectedRow : expectedData) {
				String field = expectedRow.get("Field");
				String expectedValue = expectedRow.get("Value");
				try {
					int intValue = Integer.parseInt(expectedValue);
					userResponse.body("data." + field, equalTo(intValue));
				} catch (NumberFormatException e) {
					userResponse.body("data." + field, equalTo(expectedValue));
				}
			}
		});
	}

	@Given("I have a user to update with the following data:")
	public void iHaveAUserToUpdateWithTheFollowingData(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps();
		userDataToUpdate = new HashMap<>();
		for (Map<String, String> row : data) {
			userDataToUpdate.put(row.get("Field"), row.get("Value"));
		}
	}

	@And("I update the user data of ID {int}")
	public void theUpdatedUserDataIs(int userId) {
		userAPI.updateUser(userId,userDataToUpdate);
		userUpdateResponse = SerenityRest.lastResponse();
		this.userId = userId;
	}

	@And("the updatedAt has valid date")
	public void theUpdatedAtHasValidDate() {
		String updatedAtString = userUpdateResponse.body().path("updatedAt");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		try {
			DateTime updatedAt = formatter.parseDateTime(updatedAtString);
		} catch (IllegalArgumentException e) {
			throw new AssertionError("Invalid date format for updatedAt: " + updatedAtString);
		}
	}
}
