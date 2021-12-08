package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.Address;
import pl.karas.cyclingmanagementsystem.service.AddressService;

@RequestMapping("/api")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    AddressService addressService;

    @PutMapping("/addresses")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address){
        Address savedAddress = this.addressService.save(address);
        return ResponseEntity.ok(savedAddress);
    }

}
