package edu.microservices.user.controllers;

import edu.microservices.user.services.UserRoleService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/user-role")
@Tag(name = "Users", description = "Operations on users of API")
public class UserRoleController {
    private final UserRoleService userRoleService;
}
