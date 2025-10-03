package org.medilabo.patient_service.commands.controllers;

//import org.axonframework.commandhandling.gateway.CommandGateway;
import org.medilabo.patient_service.commands.commands.AddPatientCommand;
import org.medilabo.patient_service.commands.dtos.AddNewPatientRequestDTO;
import org.medilabo.patient_service.entites.enums.EGender;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands")
public class PatientCommandController {
//    private CommandGateway commandGateway;
//
//    public PatientCommandController(CommandGateway commandGateway) {
//        this.commandGateway = commandGateway;
//    }
//
//    @PostMapping("/add")
//    public CompletableFuture<String> addNewPatient(@RequestBody AddNewPatientRequestDTO request){
//        return commandGateway.send(new AddPatientCommand(
//                3L,
//                request.getFirstName(),
//                request.getLastName(),
//                request.getDateOfBirth(),
//                request.getCreateAt(),
//                request.getGender(),
//                request.getPostalAddress(),
//                request.getPhoneNumber()
//        ));
//    }
}

