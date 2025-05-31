package com.example.blogapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Integer categoryId;

    @NotBlank(message = "Title is required")
    private String categoryTitle;

    @NotBlank(message = "Description is required")
    private String categoryDescription;
}
