package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.StationDto;
import com.flutter.project_flutter.entites.Station;

import java.util.List;

public interface IStationServices {
    public StationDto registerStation(StationDto stationDto);
    public List<StationDto> getAllStation();
    public StationDto getOneStation(int id);
    public void deleteStation(int id);
    StationDto updateStation(StationDto stationDto, int id);
}
