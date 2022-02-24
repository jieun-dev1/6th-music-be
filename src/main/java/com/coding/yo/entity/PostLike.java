package com.coding.yo.entity;

import com.coding.yo.entity.audit.AuditListener;
import com.coding.yo.entity.audit.Auditable;
import com.coding.yo.entity.audit.TimeColumns;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post_like")
@EntityListeners(AuditListener.class)

public class PostLike implements Auditable {

    @Id
    @Column(name = "post_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Embedded
    private TimeColumns timeColumns;

    public PostLike(Member member, Post post) {
        this.member = member;
        this.post = post;
    }

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {

    }
}