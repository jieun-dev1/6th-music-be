package com.coding.yo.repository;

import com.coding.yo.security.entity.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<UserDetailsImpl,String> {
}
