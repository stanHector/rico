package service.ricotunes.giftcards.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.model.Wallet;

@Data
public class UserResponse {
    @JsonProperty("user")
    private User user;

    @JsonProperty("wallet")
    private Wallet wallet;
}
