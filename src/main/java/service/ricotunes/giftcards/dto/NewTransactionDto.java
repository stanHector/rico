package service.ricotunes.giftcards.dto;

import lombok.Getter;
import lombok.Setter;
import service.ricotunes.giftcards.model.GiftCard;
import java.util.List;

@Getter
@Setter
public class NewTransactionDto {
    private double quantity;
    private double amount;
    private GiftCard giftCard;
    private Long userId;
    private String status;
    private List<String> images;
}
