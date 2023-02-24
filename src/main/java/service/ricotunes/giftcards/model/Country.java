package service.ricotunes.giftcards.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String currency;

}
