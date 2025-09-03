package com.example.UberSocketService.dto;

import java.util.List;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RideRequestDto {

    private Long passengerId;

//    private ExactLocation startLocation;
//
//    private ExactLocation endLocation;

    private List<Long> driverIds;

    private Long bookingId;
}
