package com.ibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Error Response details"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API Path",
            example = "/accounts"
    )
    private String apiPath;

    @Schema(
            description = "HTTP Status Code",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error Message",
            example = "Resource not found"
    )
    private String errorMessage;

    @Schema(
            description = "Error Time",
            example = "2021-07-01T10:00:00"
    )
    private LocalDateTime errorTime;
}
