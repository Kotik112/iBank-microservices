package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *  Create an account using a CustomerDto object.
     * @param customerDto: CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *  Fetch account details using a mobile number.
     * @param mobileNumber: String
     * @return CustomerDto object
     */
    CustomerDto fetchAccountDetails(String mobileNumber);
}
