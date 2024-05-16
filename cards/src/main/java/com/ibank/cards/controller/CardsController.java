package com.ibank.cards.controller;

import com.ibank.cards.dto.AccountsContactInfoDto;
import com.ibank.cards.dto.CardsDto;
import com.ibank.cards.dto.ErrorResponseDto;
import com.ibank.cards.dto.ResponseDto;
import com.ibank.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.ibank.cards.constants.CardsConstants.*;

@Tag(
        name = "CRUD endpoints for Cards REST API",
        description = "Collection of endpoints for managing Cards"
)
@RestController
@RequestMapping(value = "api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CardsController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    private final ICardsService cardsService;
    private final Environment environment;

    public CardsController(ICardsService cardsService, Environment environment) {
        this.cardsService = cardsService;
        this.environment = environment;
    }

    @Operation(
            summary = "Get Card details based on customer mobile number",
            description = "Endpoint for getting card details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "SUCCESS",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CardsDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "FAILURE",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCard(@RequestParam
                        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
                                                  String mobileNumber) {
        CardsDto cardsDto = cardsService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @Operation(
            summary = "Create a new Card based on customer mobile number",
            description = "Endpoint for creating a new card"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Card created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDto.class),
                            examples = @ExampleObject(
                                    name = "201",
                                    value = "{\"status\":\"201\",\"message\":\"Card created successfully\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Card already exists for the given mobile number",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class),
                            examples = @ExampleObject(
                                    name = "400",
                                    value = "{\"status\":\"400\",\"message\":\"Card already exists for the given mobile number\"}"
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam
                        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
                                                      String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Update Card details based on customer mobile number",
            description = "Endpoint for updating card details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "SUCCESS",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDto.class),
                            examples = @ExampleObject(
                                    name = "200",
                                    value = "{\"status\":\"200\",\"message\":\"Card details updated successfully\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCard(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = cardsService.updateCard(cardsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card details based on customer mobile number",
            description = "Endpoint for deleting card details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "SUCCESS",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseDto.class),
                            examples = @ExampleObject(
                                    name = "200",
                                    value = "{\"status\":\"200\",\"message\":\"Card details deleted successfully\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCard(@RequestParam
                        @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digits")
                                                      String mobileNumber) {
        boolean isDeleted = cardsService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(STATUS_417, MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get build information",
            description = "This endpoint is used to get the build information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Build information fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Build information fetch failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.ok(buildVersion);
    }


    @Operation(
            summary = "Get Java version",
            description = "This endpoint is used to get the Java version"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Java version fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Java version fetch failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Get Contact Information",
            description = "This endpoint is used to get the contact information"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contact information fetched successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Contact information fetch failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
