package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity;
    private double amount;
        @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transactions_giftCard",
            joinColumns = @JoinColumn(name = "transactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"))
    private GiftCard giftCard;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> imageList;

    private Long userId;

    private String status;

    private String remarks;
}
