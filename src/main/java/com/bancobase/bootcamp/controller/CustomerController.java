package com.bancobase.bootcamp.controller;
import com.bancobase.bootcamp.dto.CustomerDTO;
import com.bancobase.bootcamp.dto.CustomerInfoDTO;
import com.bancobase.bootcamp.dto.request.PreCustomerInfo;
import com.bancobase.bootcamp.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//Controlador es para acceso al servicio (endpoit)
@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = {"*"})
public class CustomerController {

    //@Autowired   no muy recomendada para pruebas
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerInfoDTO>> getCustomers(@RequestParam() String name) {
        List<CustomerInfoDTO> customer =  this.customerService.filterCustomersByName(name);
         return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerInfoDTO> getCustomerById(@PathVariable Long customerId){
        CustomerInfoDTO customer = this.customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody PreCustomerInfo info){
        CustomerDTO newCustomer =  this.customerService.createCustomer(info);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

}
