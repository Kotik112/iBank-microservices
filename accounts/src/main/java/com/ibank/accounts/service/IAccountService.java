package com.ibank.accounts.service;

import com.ibank.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     *  Create an account using a CustomerDto object.
     * @param customerDto: CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *  Fetch account details using a mobile number.
     * @param mobileNumber: String (Input mobile number)
     * @return CustomerDto object
     */
    CustomerDto fetchAccountDetails(String mobileNumber);

    /**
     * Update Account details using a CustomerDto object.
     * @param customerDto - CustomerDto Object (Input Customer details)
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * Delete an account using a mobile number.
     * @param mobileNumber: String (Input mobile number)
     * @return boolean indicating if the deletion of Account is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
