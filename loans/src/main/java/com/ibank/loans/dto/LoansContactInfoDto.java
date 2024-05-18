package com.ibank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Data transfer object for loans service developer contact information.
 */
@Schema(
        name = "LoansContact",
        description = "Contact information for Loans service"
)
@ConfigurationProperties(prefix = "loans")
@Getter @Setter
public class LoansContactInfoDto {
        @Schema(
                description = "Message",
                example = "For any queries, please contact the support team"
        )
        String message;
        @Schema(
                description = "Contact details",
                example = "something@email.com"
        )
        Map<String, String> contactDetails;
        @Schema(
                description = "On-call support email addresses",
                example = "something@email.com"
        )
        List<String> onCallSupport;
}
