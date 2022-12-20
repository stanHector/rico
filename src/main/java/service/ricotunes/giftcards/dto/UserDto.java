package service.ricotunes.giftcards.dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private String password;

    public UserDto() {}
}
