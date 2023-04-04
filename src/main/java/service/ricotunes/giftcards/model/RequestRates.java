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
    private String requestDate;
    private double quantity;
    private double amount;
    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> imageList;

    private Long userId;
    private String type;
    private String category;
    private String country;
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinTable(name = "request_giftCard",
            joinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"))
    private GiftCard giftCard;
}
