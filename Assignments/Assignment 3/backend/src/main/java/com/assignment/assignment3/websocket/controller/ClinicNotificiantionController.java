package com.assignment.assignment3.websocket.controller;

import com.assignment.assignment3.websocket.model.ReceiveMessage;
import com.assignment.assignment3.websocket.model.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ClinicNotificiantionController {

    @MessageMapping("/checkin")
    @SendTo("/doctor/checkin")
    public ResponseMessage checkIn(ReceiveMessage receiveMessage) throws Exception {
        return new ResponseMessage("The patient, " + receiveMessage.getPatientName()
                + ", with consultation at "
                + receiveMessage.getHour()
                + " has arrived!"
                ,receiveMessage.getDoctorName());
    }
}