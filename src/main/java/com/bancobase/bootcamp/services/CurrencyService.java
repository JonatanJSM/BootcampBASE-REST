package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.exceptions.ServiceProviderException;
import com.bancobase.bootcamp.http.APIExchangeRateClient;
import com.bancobase.bootcamp.repositories.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final APIExchangeRateClient apiExchangerateClient;

    public CurrencyService(CurrencyRepository currencyRepository, APIExchangeRateClient apiExchangerateClient) {
        this.currencyRepository = currencyRepository;
        this.apiExchangerateClient = apiExchangerateClient;
    }

    public List<CurrencyDTO> getCombinedExchangeRateInfo(){
        ExchangeRateResponse exchangeRateResponse = apiExchangerateClient.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = apiExchangerateClient.getSymbolsName();
        List<CurrencyDTO> currencyDTOList = new ArrayList<>();

        if (exchangeRateResponse != null && symbolsNameResponse !=null) {
            //       Symbol tiene la descripción y el code
                        //descripcion es el nombre largo y el code es el simbolo
            Map<String, Symbol> symbolsNames = symbolsNameResponse.getSymbols();

            for (Map.Entry<String, Double> entry : exchangeRateResponse.getRates().entrySet()) {
                String symbol = entry.getKey();
                Double value = entry.getValue();
                String name = symbolsNames.get(symbol).getDescription();

                if (name != null) {
                    // Recuerdo que dijeron que se podría evitar esto
                    // pero estuve buscando la forma, no la encontré :c
                    CurrencyDTO currencyDTO = new CurrencyDTO(name, symbol, value);
                    currencyDTOList.add(currencyDTO);
                }
            }
        } else {
            throw ServiceProviderException.builder()
                    .message("Oh no! An error occurred while connecting to our exchange rate provider.")
                    .build();
        }

        return currencyDTOList;
    }
    //Ignorar este método, estaba practicando lo que se mostró en la clase
    public List<CurrencyDTO> getCurrencies(){
        return currencyRepository
                .findAll()
                .stream()
                .map(CurrencyDTO::getFromSchema)
                .toList();
    }
}
