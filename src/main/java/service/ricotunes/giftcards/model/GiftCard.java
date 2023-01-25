package service.ricotunes.giftcards.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "giftCard")
public class GiftCard extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "giftCard_country",
            joinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Country country;

    private double rmbRate;
    private double cardRate;
    private double profit;
    private double rate;
    private double adminRate;

    //TODO
    //image
    @Column(name = "image", length = 4096, columnDefinition = "TEXT")
    private String image;
}