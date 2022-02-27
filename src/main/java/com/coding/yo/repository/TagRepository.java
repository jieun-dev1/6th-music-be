package com.coding.yo.repository;

import com.coding.yo.entity.Comment;
import com.coding.yo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
