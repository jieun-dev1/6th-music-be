package com.coding.yo.entity.audit;


public interface Auditable {
    TimeColumns getTimeColumns();
    void setTimeColumns(TimeColumns timeColumns);
}
