package com.coding.yo.security.service.post;

import com.coding.yo.entity.Member;
import com.coding.yo.entity.Post;
import com.coding.yo.repository.MemberRepository;
import com.coding.yo.security.message.request.PostDto;
import com.coding.yo.security.message.response.PostResponseDto;
import org.springframework.data.domain.Page;

public interface PostService {
    PostResponseDto toGetPostDetail(Long postId);
    Page<PostResponseDto> toGetPosts();
    void toCreatePost(PostDto postDto, Member member);
    void toEditPost(PostDto postDto, Member member);
    void toDeletePost(PostDto postDto, Member member);
}

//    void toCreateComment(CommentRequestDto commentRequestDto, Member member, Post post
//    )
