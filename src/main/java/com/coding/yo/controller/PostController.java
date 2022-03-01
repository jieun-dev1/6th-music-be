package com.coding.yo.controller;

import com.coding.yo.entity.Member;
import com.coding.yo.message.request.CommentRequestDto;
import com.coding.yo.repository.PostRepository;
import com.coding.yo.message.request.PostDto;
import com.coding.yo.message.response.PostResponseDto;
import com.coding.yo.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 필요:  controller에서는 응답으로 ReponseEntity를 직접 만들어 리턴하는것을 최소화 하고 Body만 응답할 것이고 그 외 요청에대해서는 Exception으로 처리
 * 1)  Get - 모든 포스트 조회
 * 2) Get /{postId} - 특정 포스트 상세 조회
 * 3) Post - createPost - Auhentication 필요
 * 4) Patch - /{postId} - 특정 포스트 수정 - Auhentication 필요
 * 5) Delete - /{postId} - 특정 포스트 삭제 - Auhentication 필요
 * 6) 좋아요, 신고 및 추천 게시글은 추후 개발.
 *
 */
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    //Page로 바꾸어야 함.
    @GetMapping("")
    public Page<PostResponseDto> getPosts(){
        return postService.toGetPosts();
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId) {
        return postService.toGetPostDetail(postId);
    }

    @Transactional
    @PostMapping("")
    public String createPost(@RequestBody PostDto postDto, Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        postService.toCreatePost(postDto, member);
        return "글 작성이 완료 되었습니다";
    }

    @Transactional
    @PatchMapping("/{postId}")
    public String editPost(@PathVariable Long postId, PostDto postDto, @AuthenticationPrincipal Member member) {
        postService.toEditPost(postDto, member);
        return "글 수정이 완료 되었습니다";
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable Long postId, PostDto postDto, @AuthenticationPrincipal Member member) {
        postService.toDeletePost(postDto, member);
        return "글 삭제가 완료 되었습니다";
    }

    @PostMapping("/{id}/comments")
    public String createComment(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, Authentication authentication) {
        Member member = ((Member) authentication.getPrincipal());
        postService.toCreateComment(commentRequestDto, member, id);
        return "댓글 작성이 완료 되었습니다";
    }




}
