package com.ibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Response details"
)
@Data
@AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status code"
    )
    private String statusCode;

    @Schema(
            description = "Status message"
    )
    private String statusMsg;
}
