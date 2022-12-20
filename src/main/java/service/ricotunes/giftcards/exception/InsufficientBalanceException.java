package service.ricotunes.giftcards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import service.ricotunes.giftcards.payload.response.ApiResponse;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InsufficientBalanceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ApiResponse apiResponse;

	private String message;

	public InsufficientBalanceException(ApiResponse apiResponse) {
		super();
		this.apiResponse = apiResponse;
	}

	public InsufficientBalanceException(String message) {
		super(message);
		this.message = message;
	}

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiResponse getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(ApiResponse apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
