package service.ricotunes.giftcards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "giftCard")
public class GiftCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String type;
    private double cardRate;
    private String denomination;
    private double rmbRate;
    private double profit;
    private double rate;
    private double adminRate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "giftCard_category",
            joinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Category category;

    //TODO
    //image
    @Column(name = "image", length = 4096, columnDefinition = "TEXT")
    private String image;
}