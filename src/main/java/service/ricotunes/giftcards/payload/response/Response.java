package service.ricotunes.giftcards.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response<T>  {
    private int status;
    private String message;
    private T result;
//    private String bankName;
//    private String accountNumber;
//    private double amount;
//    private String accountName;
//    private String transactionReference;
//    private double balance;
//    private long userId;
//    private TransactionType transactionType;
}