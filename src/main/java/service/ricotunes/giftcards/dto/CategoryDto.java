package service.ricotunes.giftcards.dto;

import lombok.Data;

@Data
public class CategoryDto {

    private String categoryName;
    private double cardRate;
    private String denomination;
}