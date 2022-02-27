package com.coding.yo.entity;

import com.coding.yo.entity.audit.AuditListener;
import com.coding.yo.entity.audit.Auditable;
import com.coding.yo.entity.audit.TimeColumns;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
@EntityListeners(AuditListener.class)
public class Comment implements Auditable {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

//    @Column(nullable = false, length = 50)
//    private String creator;

    @Column(name = "like_count")
    @ColumnDefault("0")
    private Integer likeCount;

    @Column(name = "report_count")
    @ColumnDefault("0")
    private Integer reportCount;

    @ColumnDefault("false")
    private Boolean blocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLike> commentLikes;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentReport> commentReports;

    @Embedded
    private TimeColumns timeColumns;

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {
        this.timeColumns = timeColumns;
    }
}