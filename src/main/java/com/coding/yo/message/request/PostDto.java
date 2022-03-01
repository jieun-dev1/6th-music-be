package com.coding.yo.message.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostDto {
    private String title;
    private String content;
    private String tags;
    private String videoId;
}
