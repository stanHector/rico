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
    private String date;
    private double quantity;
    private double amount;
    private double receivingAmount;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> imageList;

    private Long userId;
    private String status;
    private String remarks;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(name = "transactions_giftCard",
            joinColumns = @JoinColumn(name = "transactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"))
    private GiftCard giftCard;
}