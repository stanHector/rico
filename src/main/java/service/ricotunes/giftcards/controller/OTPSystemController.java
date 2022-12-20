package service.ricotunes.giftcards.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.model.OTPSystem;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class OTPSystemController {
    private Map<String, OTPSystem> otp_data = new HashMap<>();
//    private static final String ACCOUNT_SID = "ACea32fd5a750ea1e6244b1dc6cc600c29";
    private static final String ACCOUNT_SID = "ACe9986f3d04ff036f111bb0507a984cd1";
//    private static final String AUTH_ID = "e9f7bfecfdf1a36113d8739de89bea86";
    private static final String AUTH_ID = "808bf24e135652da328005e9933361b4";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    @PostMapping("otp/{phone}")
    public ResponseEntity<Object> sendOtp(@PathVariable String phone) {
        OTPSystem otpSystem = new OTPSystem();
        otpSystem.setPhone(phone);
        int min = 100000;
        int max = 999999;
        int number = (int) (Math.random() * (max - min + 1) + min);
        otpSystem.setOtp(String.valueOf(number));
        otpSystem.setExpiryTime(System.currentTimeMillis() + 60000);
        otp_data.put(phone, otpSystem);
        System.out.println("OTP: " + number);
//        Message.creator(new PhoneNumber(phone), new PhoneNumber("+18176703567"), "Your OTP is :" + otpSystem.getOtp()).create();
        Message.creator(new PhoneNumber(phone), new PhoneNumber("+15139514659"), "Your OTP is :" + otpSystem.getOtp()).create();
        return new ResponseEntity<>("OTP sent", HttpStatus.OK);
    }

    @PutMapping("otp/verify/{phone}")
    public ResponseEntity<Object> verifyOTP(@PathVariable String phone, @RequestBody OTPSystem requestBodyOtpSystem) {
        if (requestBodyOtpSystem == null || requestBodyOtpSystem.getOtp().trim().length() <= 0) {
            return new ResponseEntity<>("Please provide an OTP", HttpStatus.NOT_FOUND);
        }
        if (otp_data.containsKey(phone)) {
            OTPSystem otpSystem = otp_data.get(phone);
            if (otpSystem != null) {
                if (otpSystem.getExpiryTime() >= System.currentTimeMillis()) {
                    if (requestBodyOtpSystem.getOtp().equals(otpSystem.getOtp())) {
                        otp_data.remove(phone);
                        return new ResponseEntity<>("OTP verified successfully", HttpStatus.OK);
                    }
                    return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>("OTP has expired!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mobile number not found!", HttpStatus.NOT_FOUND);
    }
    
}
