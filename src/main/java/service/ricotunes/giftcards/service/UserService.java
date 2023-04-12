package service.ricotunes.giftcards.service;

import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.security.UserPrincipal;

import java.util.List;

@Service
public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);
	User addUser(User user);

	List<User> findAllUsers();

//	boolean verifyTransactionPin(long id, String transactionPin);
}