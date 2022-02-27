package com.coding.yo.security.message.request;

import com.coding.yo.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@Builder
public class PostDto {
    private String title;
    private String content;
    private String tags;
    private String videoId;
}
