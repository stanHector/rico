package service.ricotunes.giftcards.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double quantity;
    private double amount;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> imageList;
    private Long userId;
    private Long giftCardId;
    private String status;
    private String remarks;
}