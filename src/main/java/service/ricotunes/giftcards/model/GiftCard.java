package service.ricotunes.giftcards.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "giftCard_category",
            joinColumns = @JoinColumn(name = "giftCard_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Category category;

    private String denomination;

    private double rmbRate;

    private double cardRate;

    private double profit;

    private double rate;

    private double adminRate;

    public GiftCard() {
    }

    public GiftCard(double rmbRate, double profit, double adminRate) {
        this.rmbRate = rmbRate;
        this.cardRate = category.getCardRate();
        this.profit = profit;
        this.adminRate = adminRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDenomination() {
        String categoryName = category.getCategoryName();
        return categoryName;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public double getRmbRate() {
        return rmbRate;
    }

    public void setRmbRate(double rmbRate) {
        this.rmbRate = rmbRate;
    }

    public double getCardRate() {
        double cardRates = category.getCardRate();
        return cardRates;
    }

    public void setCardRate(double cardRate) {
        this.cardRate = cardRate;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getRate() {
        double rates = (category.getCardRate() * rmbRate) - profit;
        return rates;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAdminRate() {
        double adminRates= (category.getCardRate() * rmbRate);
        return adminRates;
    }

    public void setAdminRate(double adminRate) {
        this.adminRate = adminRate;
    }
//    @Column
//    @ElementCollection(targetClass=String.class)
//    private List<String> categoryList;

    //TODO
    //image
    @Column(name = "image", length = 4096, columnDefinition = "TEXT")
    private String image;
}