package service.ricotunes.giftcards.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {
    private Long id;
//    private String username;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
}