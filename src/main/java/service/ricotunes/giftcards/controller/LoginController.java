package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.enums.RoleName;
import service.ricotunes.giftcards.exception.AppException;
import service.ricotunes.giftcards.model.Role;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.model.Wallet;
import service.ricotunes.giftcards.payload.request.LoginRequest;
import service.ricotunes.giftcards.payload.request.SignUpRequest;
import service.ricotunes.giftcards.payload.response.ApiResponse;
import service.ricotunes.giftcards.payload.response.JwtAuthenticationResponse;
import service.ricotunes.giftcards.payload.response.UserResponse;
import service.ricotunes.giftcards.repository.RoleRepository;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.repository.WalletRepository;
import service.ricotunes.giftcards.security.JwtTokenProvider;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private static final String USER_ROLE_NOT_SET = "User role not set";

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final WalletRepository walletRepository;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        User users = userRepository.findByEmail(loginRequest.getUsernameOrEmail());
        return ResponseEntity.ok(new JwtAuthenticationResponse("Bearer " + jwt, "Login Successful!", users.getId(), users.getUsername(), users.getFullname(), users.getPhone(), users.getEmail(), users.getTransactionPin()));
//        return ResponseEntity.ok(new JwtAuthenticationResponse());
    }


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (Boolean.TRUE.equals(userRepository.existsByPhone(signUpRequest.getPhone()))) {

            return new ResponseEntity<>(new ApiResponse(false, "Phone number is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            return new ResponseEntity<>(new ApiResponse(false, "Email address is already taken", HttpStatus.CONFLICT), HttpStatus.OK);
        }

        String username = signUpRequest.getUsername().toLowerCase();
        String fullname = signUpRequest.getFullname().toLowerCase();
        String phone = signUpRequest.getPhone().toLowerCase();
        String email = signUpRequest.getEmail().toLowerCase();
        String password = passwordEncoder.encode(signUpRequest.getPassword());
        String transactionPin = signUpRequest.getTransactionPin().toLowerCase();

        User user = new User(username, fullname, phone, email, transactionPin, password);

        List<Role> roles = new ArrayList<>();

        if (userRepository.count() == 0) {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        } else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException(USER_ROLE_NOT_SET)));
        }
        user.setRoles(roles);
        User createdUser = userRepository.save(user);


        // create wallet
        Wallet wallet = new Wallet();
        wallet.setId(wallet.getId());
        wallet.setCurrentBalance(0.00);
        wallet.setUserId(user.getId());
        Wallet createdWallet = walletRepository.save(wallet);

        UserResponse response = new UserResponse();
        response.setUser(createdUser);
        response.setWallet(createdWallet);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}