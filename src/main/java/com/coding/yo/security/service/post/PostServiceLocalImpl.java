package com.coding.yo.security.service.post;

import com.coding.yo.entity.Member;
import com.coding.yo.entity.Post;
import com.coding.yo.entity.Tag;
import com.coding.yo.repository.MemberRepository;
import com.coding.yo.repository.PostRepository;
import com.coding.yo.repository.TagRepository;
import com.coding.yo.security.message.request.PostDto;
import com.coding.yo.security.message.response.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Profile("local")
public class PostServiceLocalImpl implements PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;


    /**
     * regex: split on commas and consume any spaces either side
     * @param postDto
     * @param member
     */
    @Override
    public void toCreatePost(@RequestBody PostDto postDto, Member member) {

        Post post = Post.builder()
                .member(member)
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .videoId(postDto.getVideoId())
                .build();

        postRepository.save(post);

        List<String> items = Arrays.asList(postDto.getTags().split("\\s*,\\s*"));
        List<Tag> tags = items.stream().map(tag -> new Tag(post, tag)).collect(Collectors.toList());
        tagRepository.saveAll(tags);
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
