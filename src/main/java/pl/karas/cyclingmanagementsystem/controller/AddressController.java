package pl.karas.cyclingmanagementsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.karas.cyclingmanagementsystem.model.Address;
import pl.karas.cyclingmanagementsystem.model.Training;
import pl.karas.cyclingmanagementsystem.service.AddressService;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PutMapping("/address")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address){
        Address savedAddress = this.addressService.save(address);
        return ResponseEntity.ok(savedAddress);
    }

}
