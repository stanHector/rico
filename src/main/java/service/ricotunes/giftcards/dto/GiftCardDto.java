package service.ricotunes.giftcards.dto;


import lombok.Data;

@Data
public class GiftCardDto {
    private String name;
    private String type;
    private double rmbRate;
    private double cardRate;
    private double profit;
}