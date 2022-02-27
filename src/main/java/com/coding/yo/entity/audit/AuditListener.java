package com.coding.yo.entity.audit;

import org.springframework.context.annotation.Configuration;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Configuration
public class AuditListener {
    @PrePersist
    public void setCreatedDate(Auditable auditable) {
        TimeColumns timeColumns = auditable.getTimeColumns();
        if(timeColumns == null) {
            timeColumns = new TimeColumns();
            auditable.setTimeColumns(timeColumns);
        }

        timeColumns.setCreatedAt(OffsetDateTime.now());
        timeColumns.setUpdatedAt(OffsetDateTime.now());
    }

    @PreUpdate
    public void setUpdatedDate(Auditable auditable) {
        TimeColumns timeColumns = auditable.getTimeColumns();
        timeColumns.setUpdatedAt(OffsetDateTime.now());

    }
}
