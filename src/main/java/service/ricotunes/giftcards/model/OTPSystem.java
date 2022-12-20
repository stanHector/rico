package service.ricotunes.giftcards.model;

import lombok.Data;

@Data
public class OTPSystem {
    private String phone;
    private String otp;
    private long expiryTime;
}
