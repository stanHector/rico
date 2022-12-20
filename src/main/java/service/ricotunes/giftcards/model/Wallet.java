package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "wallet")
public class Wallet extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double currentBalance;
    private Long userId;

    public Wallet() {
    }

}
