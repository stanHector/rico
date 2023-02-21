package service.ricotunes.giftcards.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSummary {
	private Long id;
	private String username;
	private String fullname;
    private String phone;
	private String email;

}
