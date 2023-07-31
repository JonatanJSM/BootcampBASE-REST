package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.services.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @RequestMapping("/currency")
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<CurrencyDTO> currencies = this.currencyService.getCombinedExchangeRateInfo();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }

}
