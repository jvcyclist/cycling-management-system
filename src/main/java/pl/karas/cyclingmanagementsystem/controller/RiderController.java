package pl.karas.cyclingmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.karas.cyclingmanagementsystem.model.Rider;
import pl.karas.cyclingmanagementsystem.service.RiderService;

import java.util.List;

@RestController
@CrossOrigin
public class RiderController {

    @Autowired
    RiderService riderService;

    @GetMapping("/api/riders")
    public List<Rider> getRiders(){
        return this.riderService.getAllRiders();
    }

}
