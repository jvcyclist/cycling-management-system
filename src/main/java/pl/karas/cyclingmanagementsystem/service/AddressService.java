package pl.karas.cyclingmanagementsystem.service;

import pl.karas.cyclingmanagementsystem.model.Address;

public interface AddressService {

    Address save(Address address);
    void delete(Long id);

}
