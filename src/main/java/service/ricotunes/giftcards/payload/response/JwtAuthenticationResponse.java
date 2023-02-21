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

    public JwtAuthenticationResponse() {
    }

        public JwtAuthenticationResponse(String accessToken, String message, long id, String username,  String fullname, String phone, String email ) {
        this.accessToken = accessToken;
        this.message = message;
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
