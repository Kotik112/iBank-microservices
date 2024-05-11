package com.ibank.cards.service.impl;

import com.ibank.cards.dto.CardsDto;
import com.ibank.cards.exception.CardAlreadyExistsException;
import com.ibank.cards.entity.Cards;
import com.ibank.cards.exception.ResourceNotFoundException;
import com.ibank.cards.repository.CardsRepository;
import com.ibank.cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static com.ibank.cards.constants.CardsConstants.CREDIT_CARD;
import static com.ibank.cards.constants.CardsConstants.NEW_CARD_LIMIT;
import static com.ibank.cards.mapper.CardsMapper.mapToCards;
import static com.ibank.cards.mapper.CardsMapper.mapToCardsDto;

@Service
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository cardsRepository;

    public CardsServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * This method is used to create a card for a given mobile number
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCard = cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for the given mobile number");
        }
        Cards card = createNewCard(mobileNumber);
        cardsRepository.save(card);

    }

    /**
     * This method is used to create a new card for a given mobile number
     *
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CREDIT_CARD);
        newCard.setTotalLimit(NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * This method is used to fetch the card details based on the mobile number
     *
     * @param mobileNumber - Input mobile Number
     * @return CardDto object - Card Details based on a given mobileNumber
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new CardAlreadyExistsException("Card does not exist for the given mobile number"));
        return mapToCardsDto(card, new CardsDto());
    }

    /**
     * This method is used to update the card details based on the input card details
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber())
        );
        mapToCards(cardsDto, card);
        cardsRepository.save(card);
        return true;
    }

    /**
     * This method is used to delete the card details based on the input mobile number
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNumber)
        );
        cardsRepository.delete(card);
        return true;
    }
}
