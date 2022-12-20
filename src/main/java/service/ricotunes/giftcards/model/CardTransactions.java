package service.ricotunes.giftcards.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="cardTransactions")
public class CardTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double quantity;
    private double amount;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "cardTransactions_giftCard",
            joinColumns = @JoinColumn(name = "cardTransactions_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"))
    private GiftCard giftCard;

//   private  List<String> stringList;
    private Long userId;
    private String status;

}
