package co.develhope.esercizio3.capitolo.interceptors.Controllers;

import co.develhope.esercizio3.capitolo.interceptors.Entities.Flight;
import co.develhope.esercizio3.capitolo.interceptors.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import static co.develhope.esercizio3.capitolo.interceptors.Enum.FlightStatus.ONTIME;


@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    public String getRandomString(){
        Random random = new Random();
        return  random.ints(97,122)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,StringBuilder::append)
                .toString();
    }
    @PostMapping("/provision")
    public List<Flight> provisionFlight(){
        List<Flight> flightList = new ArrayList<>();
        for(int i = 1; i<= 50; i++){
            Flight flight = new Flight();
            flight.setDescription(getRandomString());
            flight.setFromAirport(getRandomString());
            flight.setToAirport(getRandomString());
            flight.setFlightStatus(ONTIME);
            flightList.add(flight);
        }
        return flightRepository.saveAll(flightList);
    }


    @GetMapping("/get")
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }
}
