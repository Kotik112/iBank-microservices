package com.ibank.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Data transfer object for accounts contact information.
 */
@ConfigurationProperties(prefix = "cards")
public record AccountsContactInfoDto(
        String message,
        Map<String, String> contactDetails,
        List<String> onCallSupport
) {
}
