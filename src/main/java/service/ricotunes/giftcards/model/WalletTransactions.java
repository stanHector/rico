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

    private TransactionType transactionType;
    private double amount;

}
