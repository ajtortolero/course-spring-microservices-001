package edu.microservices.user.services;

import edu.microservices.user.models.UserRole;
import edu.microservices.user.repositories.UserRoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/roles")
@Tag(name = "Roles", description = "Operations on roles in users of API")
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    @GetMapping("")
    @Operation(
            summary = "Find all roles",
            description = "Allows you to obtain the list of all roles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Roles created in the system",
                    content = @Content(schema = @Schema(implementation = UserRole.class))
            )
    })
    public List<UserRole> getAllUserRole() {
        try {
            return userRoleRepository.findAll();
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error finding all roles", e);
        }
    }
    @GetMapping("/{role}")
    @Operation(
            summary = "Find all users for a given role",
            description = "Allows you to obtain the list of users who have a given role"
    )
    @Parameter(name = "role", description = "Role that may contain administrators to search")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Users containing the given role",
                    content = @Content(schema = @Schema(implementation = UserRole.class))
            )
    })
    public List<UserRole> findByUserEmail(String role) {
        try {
            return userRoleRepository.findByRoleContaining(role);
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error when searching for users by role", e);
        }
    }
    @PostMapping("")
    @Operation(
            summary = "Create a new role",
            description = "Allows you to create a new role")
    @Parameter(
            name = "role",
            description = "Role to create",
            content = @Content(schema = @Schema(implementation = UserRole.class)))
    @ApiResponse(responseCode = "200", description = "Role Created",
            content = @Content(schema = @Schema(implementation = UserRole.class)))
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }
}

