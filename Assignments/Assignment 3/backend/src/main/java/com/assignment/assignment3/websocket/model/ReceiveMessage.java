package com.assignment.assignment3.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ReceiveMessage {
    private String patientName;
    private String hour;
    private String doctorName;
}
