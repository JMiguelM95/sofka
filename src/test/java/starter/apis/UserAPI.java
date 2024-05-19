package starter.apis;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Map;

public class UserAPI {
	private static final String USERS_ENDPOINT = "https://reqres.in/api/users/";

	@Step
	public void searchUser(int userId) {
		SerenityRest.given()
				.contentType(ContentType.JSON)
				.baseUri(USERS_ENDPOINT+userId)
				.when()
				.get();
	}
	@Step
	public void updateUser(int userId, Map<String, String> userDataToUpdate) {
		SerenityRest.given()
				.contentType(ContentType.JSON)
				.baseUri(UserAPI.USERS_ENDPOINT + userId)
				.body(userDataToUpdate)
				.put();
	}
	@Step
	public void deleteUser(int userId) {
		SerenityRest.given()
				.contentType(ContentType.JSON)
				.baseUri(UserAPI.USERS_ENDPOINT + userId)
				.delete();
	}


}
