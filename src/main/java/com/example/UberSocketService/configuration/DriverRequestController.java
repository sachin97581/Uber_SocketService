package com.example.UberSocketService.configuration;
import com.example.UberSocketService.dto.RideRequestDto;
import com.example.UberSocketService.dto.RideResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/api/socket")
public class DriverRequestController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final RestTemplate restTemplate;

//    private final KafkaProducerService kafkaProducerService;


    public DriverRequestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.restTemplate = new RestTemplate();
//        this.kafkaProducerService = kafkaProducerService;
    }

//    @GetMapping
//    public Boolean help() {
//        kafkaProducerService.publishMessage("sample-topic", "Hello");
//        return true;
//    }

    @PostMapping("/newride")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<Boolean> raiseRideRequest(@RequestBody RideRequestDto requestDto) {
        System.out.println("request for rides received");
        sendDriversNewRideRequest(requestDto);
        System.out.println("Req completed");
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public void sendDriversNewRideRequest(RideRequestDto requestDto) {
        System.out.println("Executed periodic function");
        // TODO: Ideally the request should only go to nearby drivers, but for simplicity we send it everyone
        simpMessagingTemplate.convertAndSend("/topic/rideRequest", requestDto);
    }

    @MessageMapping("/rideResponse/{userId}")
    public void rideResponseHandler(RideResponseDto rideResponseDto) {
        System.out.println(rideResponseDto.getResponse());
    }
}