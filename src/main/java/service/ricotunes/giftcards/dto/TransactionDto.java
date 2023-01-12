package service.ricotunes.giftcards.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private long id;
    private double quantity;
    private double amount;
    private String status;
    private String remark;
}
