package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.PropertyForRent;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForRentDto;
import com.lumentech.renthire.repository.PropertyForRentRepository;
import com.lumentech.renthire.service.PropertyForRentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyForRentServiceImpl implements PropertyForRentService {

    @Autowired
    private PropertyForRentRepository propRentRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PropertyForRentDto createNewPropertyRent(PropertyForRentDto dto) {
        PropertyForRent propertyForRent = mapToEntity(dto);
        PropertyForRent newPropRent = propRentRepo.save(propertyForRent);
        return mapToDto(newPropRent);
    }

    @Override
    public PropertyForRentDto getPropertyRentDetailById(long id) {
        PropertyForRent propertyForRent = propRentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForRent", "id", id)
        );
        return mapToDto(propertyForRent);
    }

    @Override
    public PageResponse getAllPropertyRent(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PropertyForRent> propertyRentPage = propRentRepo.findAll(pageable);

        List<PropertyForRent> content = propertyRentPage.getContent();
        List<PropertyForRentDto> contentList = content.stream().
                map(property -> mapToDto(property))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPropForRentContent(contentList);
        pageResponse.setPageNo(propertyRentPage.getNumber());
        pageResponse.setPageSize(propertyRentPage.getSize());
        pageResponse.setTotalPages(propertyRentPage.getTotalPages());
        pageResponse.setTotalElements(propertyRentPage.getTotalElements());
        pageResponse.setLast(propertyRentPage.isLast());
        return pageResponse;
    }

    @Override
    public PropertyForRentDto updatePropertyRentDetails(long id, PropertyForRentDto propRentDto) {
        PropertyForRent propertyForRent = propRentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForSale", "id", id)
        );
        propertyForRent.setPropertyRentPrice(propRentDto.getPropertyRentPrice());
        propertyForRent.setPropertyAvailable(propRentDto.isPropertyAvailable());
        PropertyForRent updatePropRent = propRentRepo.save(propertyForRent);
        return mapToDto(updatePropRent);
    }

    @Override
    public void deletePropertyRentDetail(long id) {
        PropertyForRent propertyForRent = propRentRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForRent", "id", id)
        );
        propRentRepo.deleteById(id);
    }

    private PropertyForRent mapToEntity(PropertyForRentDto propRentDto){
        PropertyForRent propSale = mapper.map(propRentDto, PropertyForRent.class);
        return propSale;
    }

    private PropertyForRentDto mapToDto(PropertyForRent propRent){
        PropertyForRentDto propRentDto = mapper.map(propRent, PropertyForRentDto.class);
        return propRentDto;
    }
}
