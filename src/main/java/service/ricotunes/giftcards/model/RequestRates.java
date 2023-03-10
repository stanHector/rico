package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "requests")
public class RequestRates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String date;
    private double quantity;
    private double amount;
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> imageList;

    private Long userId;
    private String status;
    private String remarks;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(name = "request_giftCard",
            joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"))
    private GiftCard giftCard;
}
