package service.ricotunes.giftcards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

    private long id;
    private String username;
    private String fullname;
    private String phone;
    private String email;
    private String password;
    private String transactionPin;

}
