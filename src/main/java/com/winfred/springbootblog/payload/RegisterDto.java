package com.winfred.springbootblog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "RegisterDto Model Information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Schema(description = "Blog Register name")
    private String name;

    @Schema(description = "Blog Register Username")
    private String username;

    @Schema(description = "Blog Register Email")
    private String email;

    @Schema(description = "Blog Register password")
    private String password;
}
