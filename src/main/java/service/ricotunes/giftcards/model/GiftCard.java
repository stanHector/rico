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
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "type")
    private String type;

    @NotBlank
    @Column(name = "category")
    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "giftCard_country",
            joinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "country_id", referencedColumnName = "id"))
    private Country country;

//    @NotBlank
    @Column(name = "rmbRate")
    private double rmbRate;

    @Column(name = "cardRate")
    private double cardRate;

//    @NotBlank
    @Column(name = "profit")
    private double profit;

    @Column(name = "rate")
    private double rate;

//    @Column(name="buyingRate")
//    private double buyingRate;

    //TODO
    //image
    @Column(name = "image", length = 4096, columnDefinition = "TEXT")
    private String image;

}
