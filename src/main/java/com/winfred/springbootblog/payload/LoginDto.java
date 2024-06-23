package com.winfred.springbootblog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "LoginDto Model Information")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoginDto {

    @Schema(description = "Blog Login usernameOrEmail")
    private String usernameOrEmail;

    @Schema(description = "Blog Login password")
    private String password;
}
