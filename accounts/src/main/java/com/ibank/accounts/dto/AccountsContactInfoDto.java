package com.ibank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Data transfer object for accounts contact information.
 */
@Schema(
        name = "AccountsContact",
        description = "Contact information for Accounts service"
)
@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountsContactInfoDto {
        @Schema(
                description = "Message",
                example = "For any queries, please contact the support team"
        )
        private String message;

        @Schema(
                description = "Contact details",
                example = "something@email.com"
        )
        private Map<String, String> contactDetails;

        @Schema(
                description = "On-call support email addresses",
                example = "something@email.com"
        )
        private List<String> onCallSupport;

}
