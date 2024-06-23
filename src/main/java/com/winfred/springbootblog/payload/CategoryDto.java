package com.winfred.springbootblog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "CategoryDto Model Information")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;

    @Schema(description = "Blog Category name")
    private String name;

    @Schema(description = "Blog Category description")
    private String description;
}
