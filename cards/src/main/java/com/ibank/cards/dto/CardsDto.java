package com.ibank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name = "CardsDto",
        description = "Data transfer object for cards"
)
@Data
@AllArgsConstructor @NoArgsConstructor
public class CardsDto {

    @Schema(
            description = "Mobile number of the card holder",
            example = "9876543210"
    )
    @NotEmpty(message = "Card number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Card number of the card holder",
            example = "123456789012"
    )
    @NotEmpty(message = "Card number is required")
    @Pattern(regexp = "^[0-9]{12}$", message = "Card number should be 12 digits")
    private String cardNumber;

    @Schema(
            description = "Card type",
            example = "Visa"
    )
    @NotEmpty(message = "Card type is required")
    @Pattern(regexp = "^(Visa|MasterCard)$", message = "Card type should be Visa or MasterCard")
    private String cardType;

    @Schema(
            description = "Total limit of the card",
            example = "10000"
    )
    @Positive(message = "Total limit should be positive")
    private int totalLimit;

    @Schema(
            description = "Amount used from the card",
            example = "5000"
    )
    @PositiveOrZero(message = "Amount used should be positive or zero")
    private int amountUsed;

    @Schema(
            description = "Available amount in the card",
            example = "5000"
    )
    @PositiveOrZero(message = "Available amount should be positive or zero")
    private int availableAmount;
}
