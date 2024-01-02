package com.flutter.project_flutter.servicesimpl;

import com.flutter.project_flutter.dto.StationDto;
import com.flutter.project_flutter.entites.Station;
import com.flutter.project_flutter.exceptions.EntityNotFoundException;
import com.flutter.project_flutter.exceptions.InvalidOperationException;
import com.flutter.project_flutter.mappers.ApplicationMappers;
import com.flutter.project_flutter.repositories.StationRepository;
import com.flutter.project_flutter.services.IStationServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StationServices implements IStationServices {
    private StationRepository stationRepository;
    private ApplicationMappers applicationMappers;
    @Override
    public StationDto registerStation(StationDto stationDto) {
        Optional<Station> findstation = this.stationRepository.findByStationName(stationDto.getStationName());
        if(findstation.isPresent() || stationDto == null) throw new InvalidOperationException("Ce Email est deja utiliser");
        Station station = applicationMappers.convertDtoToEntity(stationDto);
        return applicationMappers
                .convertEntityToDto(
                    stationRepository.save(station)
                );

    }

    @Override
    public List<StationDto> getAllStation() {
        List<Station> liststation =  stationRepository.findAll();
        return liststation.stream().map(stt ->applicationMappers.convertEntityToDto(stt)).collect(Collectors.toList());
    }

    @Override
    public StationDto getOneStation(int id) {
        Station station =  stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Station not find"));
        return applicationMappers.convertEntityToDto(station);
    }

    @Override
    public void deleteStation(int id) {
        Station station  = stationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("station not find to delete"));
        stationRepository.delete(station);
    }

    @Override
    public StationDto updateStation(StationDto stationDto, int id) {
        if(getOneStation(id) == null) throw  new EntityNotFoundException(" La station que vous désirer modifier n'existe pas");
        Station station = applicationMappers.convertDtoToEntity(stationDto);
        station.setId(id);
        return applicationMappers.convertEntityToDto(stationRepository.save(station));
    }
}
