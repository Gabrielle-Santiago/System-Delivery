package com.gabrielle.delivery.payment.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String numberCard;
    private int cvv;
    private String type_card;
    private String type_pagament;
    private int exp_month;
    private int exp_year;

    public CardDTO(int cvv, int exp_month, int exp_year, String name, String numberCard, String type_card, String type_pagament) {
        this.cvv = cvv;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.name = name;
        this.numberCard = numberCard;
        this.type_card = type_card;
        this.type_pagament = type_pagament;
    }
}
