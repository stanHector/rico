package service.ricotunes.giftcards.payload.response;

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
