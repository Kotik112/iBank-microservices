package com.ibank.loans.controller;

import com.ibank.loans.dto.LoansContactInfoDto;
import com.ibank.loans.dto.LoansDto;
import com.ibank.loans.dto.ResponseDto;
import com.ibank.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ibank.loans.constants.LoansConstants.*;

@Tag(
        name = "CRUD endpoints for Loans REST API",
        description = "Collection of endpoints for managing Loans"
)
@RestController
@RequestMapping("/api")
public class LoansController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private LoansContactInfoDto loansContactInfoDto;

    private final ILoansService loansService;
    private final Environment environment;

    public LoansController(ILoansService loansService, Environment environment) {
        this.loansService = loansService;
        this.environment = environment;
    }

    @Operation(
            summary = "Create a new Loan",
            description = "Create a new Loan with the given mobile number"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Loan created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid mobile number"
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                            String mobileNumber) {
        loansService.createLoan(mobileNumber);
        return ResponseEntity.ok(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                            String mobileNumber) {
        LoansDto loansDto = loansService.fetchLoan(mobileNumber);
        return ResponseEntity.ok(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = loansService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.ok(new ResponseDto(STATUS_417, MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
                            String mobileNumber) {
        boolean isDeleted = loansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.ok(new ResponseDto(STATUS_417, MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get the build version of the application",
            description = "Get the build version of the application"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Version of the application"
            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }


    @Operation(
            summary = "Get the java version of the application",
            description = "Get the java version of the application"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Version of the application"
            )
    })
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(environment.getProperty("JAVA_HOME"));
    }

    @Operation(
            summary = "Contact info for the accounts-ms",
            description = "Get the contact information of the person responsible for the accounts-ms"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Contact information of the accounts"
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }
}
