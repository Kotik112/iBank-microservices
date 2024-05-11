package com.ibank.loans.controller;

import com.ibank.loans.dto.LoansDto;
import com.ibank.loans.dto.ResponseDto;
import com.ibank.loans.service.ILoansService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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

    private final ILoansService loansService;

    public LoansController(ILoansService loansService) {
        this.loansService = loansService;
    }

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
}
