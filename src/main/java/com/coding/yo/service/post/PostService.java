package com.coding.yo.service.post;

import com.coding.yo.entity.Member;
import com.coding.yo.entity.Post;
import com.coding.yo.message.request.CommentRequestDto;
import com.coding.yo.message.request.PostDto;
import com.coding.yo.message.response.CommentResponseDto;
import com.coding.yo.message.response.PostResponseDto;
import org.springframework.data.domain.Page;

public interface PostService {
    PostResponseDto toGetPostDetail(Long postId);
    Page<PostResponseDto> toGetPosts();
    void toCreatePost(PostDto postDto, Member member);
    void toEditPost(PostDto postDto, Member member);
    void toDeletePost(PostDto postDto, Member member);
    void toCreateComment(CommentRequestDto commentRequestDto, Member member, Long postId);


}

