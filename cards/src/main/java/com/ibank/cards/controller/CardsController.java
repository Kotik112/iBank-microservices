package com.ibank.cards.controller;

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

    private final ICardsService cardsService;

    public CardsController(ICardsService cardsService) {
        this.cardsService = cardsService;
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
}
