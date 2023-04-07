package service.ricotunes.giftcards.model;


import lombok.Data;
import service.ricotunes.giftcards.enums.TransactionType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wallet_transactions")
public class WalletTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bankName;
    private String accountNumber;
    private double amount;
    private String accountName;
    private String transactionReference;
    private double balance;
    private long userId;
    private String email;
    private TransactionType transactionType;
    private String status;


//    private Long walletId;
}
