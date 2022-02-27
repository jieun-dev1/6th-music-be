package com.coding.yo.security.service.post;

import com.coding.yo.entity.Member;
import com.coding.yo.repository.MemberRepository;
import com.coding.yo.security.message.request.PostDto;
import com.coding.yo.security.message.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Local 에서 header 주입 방식 대신 FirebaseToken 로직 적용 필요함
 */
@RequiredArgsConstructor
@Service
@Profile("prod")
public class PostServiceImpl implements PostService {
    private final MemberRepository memberRepository;

    @Override
    public void toCreatePost(PostDto postDto, Member member) {

    }

    @Override
    public PostResponseDto toGetPostDetail(Long postId) {
        return null;
    }

    @Override
    public Page<PostResponseDto> toGetPosts() {
        return null;
    }


    @Override
    public void toEditPost(PostDto postDto, Member member) {

    }

    @Override
    public void toDeletePost(PostDto postDto, Member member) {

    }
}
