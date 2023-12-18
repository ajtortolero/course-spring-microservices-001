package edu.microservices.user.repositories;

import edu.microservices.user.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String>  {
    List<UserRole> findByRoleContaining(String role);
}
