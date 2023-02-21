package service.ricotunes.giftcards.payload;


import lombok.Data;

@Data
public abstract class UserDateAuditPayload extends DateAuditPayload {
	private Long createdBy;

	private Long updatedBy;

}
