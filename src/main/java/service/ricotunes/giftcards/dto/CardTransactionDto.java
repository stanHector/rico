package service.ricotunes.giftcards.dto;

import lombok.Data;

@Data
public class CardTransactionDto {
    private long id;
    private double quantity;
    private double amount;
    private String status;
}
