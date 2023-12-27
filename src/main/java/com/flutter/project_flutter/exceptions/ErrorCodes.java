package com.flutter.project_flutter.exceptions;

public enum ErrorCodes {
    User_Not_Found(100),
    User_Not_Valid(101),
    TypeAbonnement_Not_Found(200),
    TypeAbonnement_Not_Valid(201),
    Abonnement_Not_Found(300),
    Abonnement_Not_Valid(301),
    Station_Not_Found(400),
    Station_Not_Valid(401),
    DepenseEssence_Not_Found(501),
    DepenseEssence_Not_Valid(501);

    private int code;
    ErrorCodes(int code) { this.code = code;}

    public int getCode() {
        return code;
    }
}
