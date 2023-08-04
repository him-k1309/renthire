package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Property;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PropertyDto;
import com.lumentech.renthire.repository.PropertyRepository;
import com.lumentech.renthire.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepo;

    private ModelMapper mapper;

    public PropertyServiceImpl(PropertyRepository propertyRepo, ModelMapper mapper){
        this.propertyRepo = propertyRepo;
        this.mapper = mapper;
    }

    @Override
    public PropertyDto createNewProperty(PropertyDto dto) {
        Property prop = mapToEntity(dto);
        Property newProperty = propertyRepo.save(prop);
        PropertyDto propDto = mapToDto(newProperty);
        return propDto;
    }

    @Override
    public PropertyDto getPropertyDetailById(long id) {
        Property property = propertyRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Property", "id", id)
        );
        return mapToDto(property);
    }

    private PropertyDto mapToDto(Property newProperty) {
        PropertyDto dtoMap = mapper.map(newProperty, PropertyDto.class);
        return dtoMap;
    }

    private Property mapToEntity(PropertyDto dto) {
        Property propMap = mapper.map(dto, Property.class);
        return propMap;
    }
}
