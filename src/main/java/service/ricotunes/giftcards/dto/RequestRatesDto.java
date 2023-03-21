package service.ricotunes.giftcards.dto;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "requests")
public class RequestRatesDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double quantity;
    private double amount;
    private String type;
    private String country;
    private String comment;
}
