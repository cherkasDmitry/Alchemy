package com.alchemy.repositories;

import com.alchemy.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, String> {

    UserRoles findByName(String name);
}
