package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.PropertyForSale;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.PropertyForSaleDto;
import com.lumentech.renthire.repository.PropertyForSaleRepository;
import com.lumentech.renthire.service.PropertyForSaleService;
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
public class PropertyForSaleServiceImpl implements PropertyForSaleService {

    @Autowired
    private PropertyForSaleRepository propSaleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PropertyForSaleDto createNewPropertySale(PropertyForSaleDto dto) {
        PropertyForSale propertyForSale = mapToEntity(dto);
        PropertyForSale newPropSale = propSaleRepo.save(propertyForSale);
        return mapToDto(newPropSale);
    }

    @Override
    public PropertyForSaleDto getPropertySaleDetailById(long id) {
        PropertyForSale propertyForSale = propSaleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForSale", "id", id)
        );
        return mapToDto(propertyForSale);
    }

    @Override
    public PageResponse getAllPropertySale(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<PropertyForSale> propertySalePage = propSaleRepo.findAll(pageable);

        List<PropertyForSale> content = propertySalePage.getContent();
        List<PropertyForSaleDto> contentList = content.stream().
                map(property -> mapToDto(property))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPropForSaleContent(contentList);
        pageResponse.setPageNo(propertySalePage.getNumber());
        pageResponse.setPageSize(propertySalePage.getSize());
        pageResponse.setTotalPages(propertySalePage.getTotalPages());
        pageResponse.setTotalElements(propertySalePage.getTotalElements());
        pageResponse.setLast(propertySalePage.isLast());
        return pageResponse;
    }

    @Override
    public PropertyForSaleDto updatePropertySaleDetails(long id, PropertyForSaleDto propSaleDto) {
        PropertyForSale propertyForSale = propSaleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForSale", "id", id)
        );
        propertyForSale.setPropertySuggestedPrice(propSaleDto.getPropertySuggestedPrice());
        propertyForSale.setPropertyAvailable(propSaleDto.isPropertyAvailable());
        PropertyForSale updatePropSale = propSaleRepo.save(propertyForSale);
        return mapToDto(updatePropSale);
    }

    @Override
    public void deletePropertySaleDetail(long id) {
        PropertyForSale propertyForSale = propSaleRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("PropertyForSale", "id", id)
        );
        propSaleRepo.deleteById(id);
    }

    private PropertyForSale mapToEntity(PropertyForSaleDto propSaleDto){
        PropertyForSale propSale = mapper.map(propSaleDto, PropertyForSale.class);
        return propSale;
    }

    private PropertyForSaleDto mapToDto(PropertyForSale propSale){
        PropertyForSaleDto propSaleDto = mapper.map(propSale, PropertyForSaleDto.class);
        return propSaleDto;
    }
}
