package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

import static com.eazybytes.accounts.constants.AccountsConstants.*;

@Tag(name = "CRUD endpoints for Accounts", description = "Endpoints for managing customer accounts")
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private final IAccountService accountService;

    public AccountsController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(
            summary = "Create a new account for the customer",
            description = "This endpoint is used to create a new account for the customer"
    )
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @Operation(
            summary = "Fetch account details",
            description = "This endpoint is used to fetch account details for the customer"
    )
    @ApiResponse(responseCode = "200", description = "Account details fetched successfully")
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                               String mobileNumber) {
        CustomerDto customerDetails = accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.ok()
                .body(customerDetails);
    }

    @Operation(
            summary = "Update account and customer details",
            description = "This endpoint is used to update account details for the customer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Account details update failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)

                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.ok()
                    .body(new ResponseDto(STATUS_200, "Account details updated successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(STATUS_500, "Account details update failed"));
        }
    }

    @Operation(
            summary = "Delete account and the customer details",
            description = "This endpoint is used to delete account and customer details for the customer"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account details deleted successfully"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Account details deletion failed",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(STATUS_500, MESSAGE_500));
        }
    }

}
