package service.ricotunes.giftcards.dto;


import lombok.Data;

@Data
public class AccountDto {
    private long id;
    private String accountName;
    private String bankName;
    private String accountNumber;

}
