package com.ibank.cards.service;

import com.ibank.cards.dto.CardsDto;

public interface ICardsService {
    /**
     * This method is used to create a card for a given mobile number
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     * This method is used to fetch the card details based on the mobile number
     *
     * @param mobileNumber - Input mobile Number
     *  @return Card Details based on a given mobileNumber
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     * This method is used to update the card details based on the input card details
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     * This method is used to delete the card details based on the input mobile number
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);
}
