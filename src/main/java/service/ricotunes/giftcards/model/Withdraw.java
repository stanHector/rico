package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "withdraw")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bankName;
    private String accountNumber;
    private double amount;
    private String accountName;
    private String transactionReference;
    private double remainingBalance;
    private double initialBalance;
    private long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "withdraw_wallet",
            joinColumns = @JoinColumn(name = "withdraw_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "wallet_id", referencedColumnName = "id"))
    private Wallet wallet;
}
