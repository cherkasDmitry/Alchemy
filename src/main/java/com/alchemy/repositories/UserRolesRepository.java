package com.alchemy.repositories;

import com.alchemy.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, String> {

    Optional<UserRole> findByName(String name);
}
