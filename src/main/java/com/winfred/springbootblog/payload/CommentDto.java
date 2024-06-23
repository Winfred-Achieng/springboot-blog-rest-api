package com.winfred.springbootblog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "CommentDto Model Information")
public class CommentDto {
    private long id;
    @NotEmpty(message = "Name should not be null or empty")

    @Schema(description = "Blog Comment name")
    private String name;

    @Schema(description = "Blog Comment email")
    @NotEmpty
    @Email
    private String email;

    @Schema(description = "Blog Comment body")
    @NotEmpty
    @Size(min = 10, message = "Comment body should be minimum 10 characters")
    private String body;
}
