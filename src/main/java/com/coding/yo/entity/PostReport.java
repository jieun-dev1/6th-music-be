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
@Table(name = "post_report")
@EntityListeners(AuditListener.class)

public class PostReport implements Auditable {
    @Id
    @Column(name = "post_report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private TimeColumns timeColumns;

    public PostReport(Post post, Member member) {
        this.post = post;
        this.member = member;
    }

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {

    }
}