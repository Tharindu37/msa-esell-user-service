package com.esell.esell_user_service.repo;

import com.esell.esell_user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
