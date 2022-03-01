//package com.coding.yo.service.post;
//
//import com.coding.yo.entity.Member;
//import com.coding.yo.entity.Post;
//import com.coding.yo.message.request.CommentRequestDto;
//import com.coding.yo.message.response.CommentResponseDto;
//import com.coding.yo.repository.MemberRepository;
//import com.coding.yo.message.request.PostDto;
//import com.coding.yo.message.response.PostResponseDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
///**
// * Front 개발 시 사용 예정
// * Local 에서 header 주입 방식 대신 FirebaseToken 로직 적용 필요함
// */
//@RequiredArgsConstructor
//@Service
//@Profile("prod")
//public class PostServiceImpl implements PostService {
//    private final MemberRepository memberRepository;
//
//    @Override
//    public void toCreatePost(PostDto postDto, Member member) {
//
//    }
//
//    @Override
//    public PostResponseDto toGetPostDetail(Long postId) {
//        return null;
//    }
//
//    @Override
//    public Page<PostResponseDto> toGetPosts() {
//        return null;
//    }
//
//
//    @Override
//    public void toEditPost(PostDto postDto, Member member) {
//
//    }
//
//    @Override
//    public void toDeletePost(PostDto postDto, Member member) {
//
//    }
//
//    @Override
//    public void toCreateComment(CommentRequestDto commentRequestDto, Member member, Long postId) {
//
//    }
//
//
//
//}
