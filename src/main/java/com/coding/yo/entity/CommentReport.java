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
@Table(name = "comment_report")
@EntityListeners(AuditListener.class)
public class CommentReport implements Auditable {

    @Id
    @Column(name = "comment_report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private TimeColumns timeColumns;

    @Override
    public void setTimeColumns(TimeColumns timeColumns) {
        this.timeColumns = timeColumns;
    }
}
