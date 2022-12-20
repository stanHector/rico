package service.ricotunes.giftcards.exception;


import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.model.Wallet;

public class WalletDoesNotBelongToUser extends Exception {
    public WalletDoesNotBelongToUser(User user, Wallet wallet) {
        super("Customer with id"+ user.getId()+" does not have associated walletId : "+wallet.getId());
    }
}
