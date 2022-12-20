package service.ricotunes.giftcards.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "banks")
public class Banks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String bankCode;
}
