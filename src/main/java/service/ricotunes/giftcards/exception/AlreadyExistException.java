package service.ricotunes.giftcards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.ricotunes.giftcards.payload.response.ApiResponse;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ApiResponse apiResponse;

	public AlreadyExistException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	public AlreadyExistException(String message) {
		super(message);
	}



	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiResponse getApiResponse() {
		return apiResponse;
	}
}
