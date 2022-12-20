package service.ricotunes.giftcards.exception;


import service.ricotunes.giftcards.model.User;

public class UserAlreadyHasWalletException extends Exception {
    public UserAlreadyHasWalletException(User user) {
//        super("User "+ user.getFirstname()+" "+ user.getLastname()+" already owns a wallet : ");
        super("User "+ user.getFullname()+" already owns a wallet"); //: "+ user.getWallet().getId());
    }
}
