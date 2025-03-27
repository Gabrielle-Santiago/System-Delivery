package com.gabrielle.delivery.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabrielle.delivery.errorResponse.ErrorResponse;
import com.gabrielle.delivery.payment.dto.CardDTO;
import com.gabrielle.delivery.payment.validate.ValidateCard;

@RestController
@RequestMapping("payment")
public class CardController {
    
    @PostMapping("/number-card")
    public ResponseEntity<?> ValidateCard(@RequestBody CardDTO card){
        if (!ValidateCard.validatenumberCard(card.getNumberCard())) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid card number!"));
        }
        return ResponseEntity.ok(card);
    }
}
