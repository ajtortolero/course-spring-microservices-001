package edu.microservices.user.services;

import edu.microservices.user.models.User;
import edu.microservices.user.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User getByUserID(String id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> getAllUser() {
        try {
            return userRepository.findAll();
        } catch (DataAccessException e) {
            log.error("Error searching for all users", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error searching for all users", e);
        }
    }
    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (DataAccessException e) {
            log.error("Error saving user", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving user", e);
        }
    }
    public User updateUser(User user) {
        try {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            Objects.requireNonNull(existingUser).setName(user.getName());
            Objects.requireNonNull(existingUser).setLastName(user.getLastName());
            Objects.requireNonNull(existingUser).setEmail(user.getEmail());
            Objects.requireNonNull(existingUser).setLogin(user.getLogin());
            Objects.requireNonNull(existingUser).setPassword(user.getEmail());
            return userRepository.save(existingUser);
        } catch (DataAccessException e) {
            log.error("Error updating user", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error saving user", e);
        }
    }
    public String deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.deleteById(user.getId());
            log.info("The user with Id= " + id + " has been deleted");
            return "The user with Id= " + id + " has been deleted";
        } else {
            log.error("User with Id=" + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with Id=" + id + " not found");
        }
    }
}

