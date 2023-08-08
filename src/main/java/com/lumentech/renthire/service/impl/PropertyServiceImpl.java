package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Property;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyDto;
import com.lumentech.renthire.repository.PropertyRepository;
import com.lumentech.renthire.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
        dto.setPropertyDateAdd(Instant.now());
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

    @Override
    public PageResponse getAllProperty(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Property> propertyPage = propertyRepo.findAll(pageable);

        List<Property> content = propertyPage.getContent();
        List<PropertyDto> contentList = content.stream().
                map(property -> mapToDto(property))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPropContent(contentList);
        pageResponse.setPageNo(propertyPage.getNumber());
        pageResponse.setPageSize(propertyPage.getSize());
        pageResponse.setTotalPages(propertyPage.getTotalPages());
        pageResponse.setTotalElements(propertyPage.getTotalElements());
        pageResponse.setLast(propertyPage.isLast());
        return pageResponse;
    }

    @Override
    public PropertyDto updatePropertyDetails(long id, PropertyDto propDto) {
        Property property = propertyRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Property", "propertyId", id)
        );
        property.setPropertyAddress(propDto.getPropertyAddress());
        property.setPropertyDescription(propDto.getPropertyDescription());
        property.setPropertyNumOfBedroom(propDto.getPropertyNumOfBedroom());
        property.setPropertyNumOfBathroom(propDto.getPropertyNumOfBathroom());
        property.setPropertyNumOfGarage(propDto.getPropertyNumOfGarage());
        property.setPropertyNumOfRooms(propDto.getPropertyNumOfRooms());

        Property update = propertyRepo.save(property);
        return mapToDto(update);
    }

    @Override
    public void deletePropertyDetail(long id) {
        Property property = propertyRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Property", "propertyId", id)
        );
        propertyRepo.deleteById(id);
    }

    private PropertyDto mapToDto(Property property) {
        PropertyDto dtoMap = mapper.map(property, PropertyDto.class);
        return dtoMap;
    }

    private Property mapToEntity(PropertyDto dto) {
        Property propMap = mapper.map(dto, Property.class);
        return propMap;
    }
}
