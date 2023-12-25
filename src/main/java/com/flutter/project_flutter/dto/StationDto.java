package com.flutter.project_flutter.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StationDto {
    //private int id;
    private String stationName;
    private String longitude;
    private String latitude;
    private boolean status = true;
    private int partener_id;


}
