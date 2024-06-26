package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private UUID categoryId;
    private String name;
    private String description;
    private User user;
}
