package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Account details"
)
@Data
public class AccountsDto {
    @Schema(
            description = "Account number",
            example = "1234567890"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch address",
            example = "123, Main Street, New York"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
