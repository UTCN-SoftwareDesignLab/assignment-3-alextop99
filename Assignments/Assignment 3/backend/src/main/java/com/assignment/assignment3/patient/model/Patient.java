package com.assignment.assignment3.patient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "idcardnumber"),
                @UniqueConstraint(columnNames = "cnp")
        })
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String firstname;

    @Column(nullable = false, length = 20)
    private String lastname;

    @Column(nullable = false, length = 8)
    private String idcardnumber;

    @Column(nullable = false, length = 13)
    private String cnp;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false, length = 120)
    private String address;
}
