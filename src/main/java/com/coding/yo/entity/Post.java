package com.coding.yo.entity;

import com.coding.yo.entity.audit.AuditListener;
import com.coding.yo.entity.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import com.coding.yo.entity.audit.TimeColumns;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
@EntityListeners(AuditListener.class)

public class Post implements Auditable {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

//    @Column(nullable = false, length = 50)
//    private String creator;

    @Column(name = "like_count")
    @ColumnDefault("0")
//    private Integer likeCount = 0;
    private Integer likeCount;

    @Transient
    @ColumnDefault("false")
    private Boolean liked = Boolean.FALSE;

    @Column(name = "report_count")
    @ColumnDefault("0")
    private Integer reportCount;

    @Transient
    @ColumnDefault("false")
    private Boolean blocked = Boolean.FALSE;

    @Column(name = "video_id", nullable = false, length = 20)
    private String videoId;

    @Embedded
    private TimeColumns timeColumns;

    @OneToMany(mappedBy = "post")
    private Set<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostReport> postReports;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Builder
    public Post(String title, String content, String videoId, Member member){
        this.title = title;
        this.content = content;
        this.videoId = videoId;
        this.member = member;
    }

    public void likePost(Integer likeCount, Boolean liked) {
        this.likeCount = likeCount;
        this.liked = liked;
    }

    public void blockPost(Boolean blocked) {
        this.blocked = blocked;
    }

    public void countBlock(Integer reportCount) {
        this.reportCount = reportCount;
    }

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {
        this.timeColumns = timeColumns;
    }
}