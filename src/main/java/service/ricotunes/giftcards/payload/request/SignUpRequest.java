package service.ricotunes.giftcards.payload.request;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

    @NotBlank
    @Column(name = "fullname")
    @Size(max = 15)
    private String fullname;

    @NotBlank
    @Column(name = "phone")
    @Size(max = 15)
    private String phone;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

//    @NotBlank
    @Size(max = 40)
    private String transactionPin;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;


}
