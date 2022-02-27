package com.coding.yo.entity.audit;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.OffsetDateTime;


/**
 * setter 는 refactoring 때 메서드로 변경하기.
 */

@Getter
@Setter
@Embeddable
public class TimeColumns {
    @Column(name = "created_at", updatable=false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime updatedAt;
}
