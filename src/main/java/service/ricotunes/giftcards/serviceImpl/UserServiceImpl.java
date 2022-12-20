package service.ricotunes.giftcards.serviceImpl;

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

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserSummary getCurrentUser(UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFullname(), currentUser.getPhone(),
                currentUser.getEmail());
    }

//    @Override
//    public UserIdentityAvailability checkUsernameAvailability(String username) {
//        Boolean isAvailable = !userRepository.existsByUsername(username);
//        return new UserIdentityAvailability(isAvailable);
//    }

//    @Override
//    public UserIdentityAvailability checkEmailAvailability(String email) {
//        Boolean isAvailable = !userRepository.existsByEmail(email);
//        return new UserIdentityAvailability(isAvailable);
//    }
//
//    @Override
//    public UserProfile getUserProfile(String username) {
//        User users = userRepository.getUserByName(username);
//
//        return new UserProfile(users.getId(), users.getFirstname(), users.getLastname(), users.getPhone(),
//                users.getEmail()
//        );
//    }

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

//    @Override
//    public ApiResponse giveAdmin(String username) {
//        User users = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
//                .orElseThrow(() -> new AppException("User role not set")));
//        roles.add(
//                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
//        users.setRoles(roles);
//        userRepository.save(users);
//        return new ApiResponse(Boolean.TRUE, "You gave ADMIN role to users: " + username);
//    }

//    @Override
//    public ApiResponse removeAdmin(String username) {
//        User users = userRepository.getUserByName(username);
//        List<Role> roles = new ArrayList<>();
//        roles.add(
//                roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set")));
//        users.setRoles(roles);
//        userRepository.save(users);
//        return new ApiResponse(Boolean.TRUE, "You took ADMIN role from users: " + username);
//    }

//    @Override
//    public User updateUser(User newUsers, String username, UserPrincipal currentUser) {
//        User users = userRepository.getUserByName(username);
//        if (users.getId().equals(currentUser.getId())
//                || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
////            users.setUsername(newUsers.getUsername());
//            users.setEmail(newUsers.getEmail());
//            users.setPhone(newUsers.getPhone());
//            users.setPassword(passwordEncoder.encode(newUsers.getPassword()));
//
//            return userRepository.save(users);
//
//        }
//
//        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to update profile of: " + username);
//        throw new UnauthorizedException(apiResponse);
//
//    }

//    @Override
//    public ApiResponse deleteUser(String username, UserPrincipal currentUser) {
//        User users = userRepository.findByUsername(username)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", username));
//        if (!users.getId().equals(currentUser.getId()) || !currentUser.getAuthorities()
//                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))) {
//            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "You don't have permission to delete profile of: " + username);
//            throw new AccessDeniedException(apiResponse);
//        }
//
//        userRepository.deleteById(users.getId());
//
//        return new ApiResponse(Boolean.TRUE, "You successfully deleted profile of: " + username);
//    }
}
