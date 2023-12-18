package edu.microservices.user.controllers;

import edu.microservices.user.models.User;
import edu.microservices.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user")
@Tag(name = "Users", description = "Operations on users of API")
public class UserController {
    private final UserService userService;
    @GetMapping("")
    @Operation(
            summary = "Get all users",
            description = "Allows you to obtain the list of all users"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users created in the system",
                    content = @Content(schema = @Schema(implementation = User.class)))
    })
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Query a user by ID",
            description = "Allows you to consult a user by ID"
    )
    @Parameter(
            name = "Id",
            description = "User ID to search")
    @ApiResponse(
            responseCode = "200", description = "User found",
            content = @Content(schema = @Schema(implementation = User.class))
    )
    public User getByUserID(@PathVariable String id) {
        return userService.getByUserID(id);
    }
    @PostMapping("")
    @Operation(
            summary = "Create a new user",
            description = "Allows you to create a new user")
    @Parameter(
            name = "User",
            description = "User to create",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(
            responseCode = "200",
            description = "User Created",
            content = @Content(schema = @Schema(implementation = User.class)))
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PutMapping("")
    @Operation(
            summary = "Update a user",
            description = "Allows you to update an existing user"
    )
    @Parameter(
            name = "User",
            description = "User to update",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(
            responseCode = "200",
            description = "User updated",
            content = @Content(schema = @Schema(implementation = User.class)))
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(
            summary = "Delete a user by Id",
            description = "Allows you to delete a user, given an Id"
    )
    @Parameter(
            name = "Id",
            description = "Id of the user to delete"
    )
    @ApiResponse(
            responseCode = "204", description = "User deleted"
    )
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getStatusCode());
        }
    }
}
