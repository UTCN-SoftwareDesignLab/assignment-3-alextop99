package com.assignment.assignment3;

public class UrlMapping {
    public static final String API_PATH = "/api";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
    public static final String PATIENTS = API_PATH + "/patients";
    public static final String CONSULTATIONS = API_PATH + "/consultations";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_ALL_DOCTORS = "/find-all-doctors";
    public static final String FIND_ALL_BY_DATE_AND_DOCTOR = "/find-all-by-date-and-doctor";
    public static final String FIND_ALL_BY_PATIENT_AND_DOCTOR = "/find-all-by-patient-and-doctor";
    public static final String FIND_BY_ID = "/{id}";
    public static final String SAVE = "/save";
    public static final String UPDATE = "/update";
    public static final String DELETE = FIND_BY_ID;
}