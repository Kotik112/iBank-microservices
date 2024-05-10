package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static com.eazybytes.accounts.constants.AccountsConstants.ADDRESS;
import static com.eazybytes.accounts.constants.AccountsConstants.SAVINGS;
import static com.eazybytes.accounts.mapper.AccountsMapper.mapToAccountsDto;
import static com.eazybytes.accounts.mapper.CustomerMapper.mapToCustomer;
import static com.eazybytes.accounts.mapper.CustomerMapper.mapToCustomerDto;

@Service
public class AccountServiceImpl implements IAccountService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository, CustomerRepository customerRepository1) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository1;
    }

    /**
     * This method is used to create a new account for the customer.
     * @param customerDto: CustomerDto object
     */
    @Transactional
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = mapToCustomer(customerDto, new Customer());
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customer.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);

        Accounts newAccount = createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(SAVINGS);
        newAccount.setBranchAddress(ADDRESS);
        return newAccount;
    }

    /**
     * Fetch account details using a mobile number.
     *
     * @param mobileNumber : Input mobile number
     * @return CustomerDto object
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );
        CustomerDto customerDto = mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(mapToAccountsDto(account, new AccountsDto()));
        return customerDto;

    }

    /**
     * Update Account details using a CustomerDto object.
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     * Delete an account using a mobile number.
     *
     * @param mobileNumber : String (Input mobile number)
     * @return boolean indicating if the deletion of Account is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        accountsRepository.delete(account);
        customerRepository.deleteByCustomerId(customer.getCustomerId());
        return true;
    }

}
