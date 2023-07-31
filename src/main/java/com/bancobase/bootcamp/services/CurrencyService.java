package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyDTO> getCurrencies(){
        return currencyRepository
                .findAll()
                .stream()
                .map(CurrencyDTO::getFromSchema)
                .toList();
    }
}
