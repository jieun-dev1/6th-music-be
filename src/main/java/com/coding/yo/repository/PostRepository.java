package com.coding.yo.repository;

import com.coding.yo.entity.Member;
import com.coding.yo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Optional<Post> findById(Long postId);
        Optional<Post> findById(Long id);

}
