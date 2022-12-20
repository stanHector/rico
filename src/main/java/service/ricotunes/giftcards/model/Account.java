package service.ricotunes.giftcards.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String accountName;
    private String bankName;
    private String accountNumber;
//    private String accountType;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinTable(name = "account_user",
//            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Long userId;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinTable(name = "account_wallet",
//            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "wallet_id", referencedColumnName = "id"))
    private Long walletId;

    public Account() {
    }

}
