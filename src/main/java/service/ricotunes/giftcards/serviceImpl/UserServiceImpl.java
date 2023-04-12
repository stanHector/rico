package service.ricotunes.giftcards.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import service.ricotunes.giftcards.enums.RoleName;
import service.ricotunes.giftcards.exception.AppException;
import service.ricotunes.giftcards.exception.EmailExistsException;
import service.ricotunes.giftcards.exception.UsernameExistsException;
import service.ricotunes.giftcards.model.Role;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.payload.UserSummary;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.repository.RoleRepository;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.security.UserPrincipal;
import service.ricotunes.giftcards.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFullname(), currentUser.getPhone(),
                currentUser.getEmail());
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new UsernameExistsException(apiResponse);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            throw new EmailExistsException(apiResponse);
        }

        List<Role> roles = new ArrayList<>();
        roles.add(
                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}