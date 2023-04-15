package service.ricotunes.giftcards.payload.response;

import lombok.Data;

@Data
public class JwtAuthenticationResponse<T> {
    private String accessToken;
    private String message = "Login Successful";
    private Long id;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private String transactionPin;

    public JwtAuthenticationResponse() {
    }

        public JwtAuthenticationResponse(String accessToken, String message, long id, String username,  String fullname, String phone, String email, String transactionPin ) {
        this.accessToken = accessToken;
        this.message = message;
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.transactionPin = transactionPin;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
