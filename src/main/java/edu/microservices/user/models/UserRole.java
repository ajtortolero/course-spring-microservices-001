package edu.microservices.user.models;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends User {
    @Schema(description = "UserRole name", example = "Admin")
    protected String role;
    @Override
    public String toString() {
        return "UserRole {" +
                ", name='" + role + '\'' +
                '}';
    }
}