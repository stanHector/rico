package service.ricotunes.giftcards.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.UserDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.payload.response.Response;
import service.ricotunes.giftcards.repository.UserRepository;
import service.ricotunes.giftcards.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;


//    private final SimpMessagingTemplate webSocket;

    //get all users
    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    //verify transaction pin
    @PostMapping("users/verify/transaction_pin")
    public ResponseEntity<Object>verifyTransactionPin(@RequestBody String transactionPin) {
        User user = userRepository.findByTransactionPin(transactionPin);

       System.out.println("Entered transaction pin" + transactionPin);

        System.out.println("User Pin: " + user.getTransactionPin());

        if (user == null) {
            return new ResponseEntity<>(new Response(404, "Transaction pin not found", transactionPin), HttpStatus.OK);
        }
        if (!user.getTransactionPin().equals(transactionPin)) {
            return new ResponseEntity<>(new Response(400, "Incorrect Pin", transactionPin), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(200, "Pin Verifie", transactionPin), HttpStatus.OK);

    }

    //get user by Id
    @GetMapping("user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
        return ResponseEntity.ok().body(user);
    }

    //update password
    @PatchMapping("user/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public User updatePassword(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        User users = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        final User updatedUser = userRepository.save(users);
        System.out.println("Updated User Password " + updatedUser);
        return userRepository.save(updatedUser);
    }


    @PutMapping("otp/verify")
    public ResponseEntity<Object> verifyPin(@RequestBody User user) {
        User users = userRepository.findById(user.getId()).get();

        System.out.println("UserTransaction:: " + users.getTransactionPin());

        System.out.println(" Entered transaction pin ::: " + user.getTransactionPin());

        if (users != null) {
            return new ResponseEntity<>("User not found with this id", HttpStatus.NOT_FOUND);
        }
            if (users.getTransactionPin().equals(user.getTransactionPin())) {
                return new ResponseEntity<>("Pin verified successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Pin not verified", HttpStatus.BAD_REQUEST);
        }


    //update user details
    @PutMapping("user/{id}")
    @PreAuthorize("hasRole('USER')or hasRole('ADMIN')")
    public User updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        final User updatedUser = userRepository.save(user);
        System.out.println("Updated User " + updatedUser);
        return userRepository.save(updatedUser);
    }

    //delete user
    @DeleteMapping("user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
