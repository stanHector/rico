package service.ricotunes.giftcards.exception;

public class InsufficientBalanceInWalletException extends Exception {

    public InsufficientBalanceInWalletException(long walletId) {
        super("Wallet with walletId : "+walletId+" does not have sufficient balance");
    }
}
