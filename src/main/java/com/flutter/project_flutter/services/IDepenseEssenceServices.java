package com.flutter.project_flutter.services;

import com.flutter.project_flutter.dto.DepenseEssenceDto;
import com.flutter.project_flutter.dto.DepenseEssenceDtoEntity;

import java.util.List;

public interface IDepenseEssenceServices {
    public DepenseEssenceDtoEntity registerDepenseEssence(DepenseEssenceDto depenseEssenceDto);
    public List<DepenseEssenceDtoEntity> getAllDepenseEssence();
    public DepenseEssenceDtoEntity getOneDepenseEssence(int id);
    public void deleteDepenseEssence(int id);
}
