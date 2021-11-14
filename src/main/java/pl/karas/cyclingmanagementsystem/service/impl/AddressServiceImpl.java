package pl.karas.cyclingmanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karas.cyclingmanagementsystem.model.Address;
import pl.karas.cyclingmanagementsystem.repository.AddressRepository;
import pl.karas.cyclingmanagementsystem.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return this.addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        this.addressRepository.deleteById(id);
    }

}
