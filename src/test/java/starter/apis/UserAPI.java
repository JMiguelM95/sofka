package starter.apis;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.Map;

public class UserAPI {
	private static final String USERS_ENDPOINT = "https://reqres.in/api/users/";

	public void searchUser(int userId) {
		SerenityRest.given()
				.contentType(ContentType.JSON)
				.baseUri(USERS_ENDPOINT+userId)
				.when()
				.get();
	}

	public void updateUser(int userId, Map<String, String> userDataToUpdate) {
		SerenityRest.given()
				.contentType(ContentType.JSON)
				.baseUri(UserAPI.USERS_ENDPOINT + userId)
				.body(userDataToUpdate)
				.put();
	}


}
