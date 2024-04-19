package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private Integer categoryId;
    private String name;
    private String description;
    private User user;
//    long currentTimeInMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
}
