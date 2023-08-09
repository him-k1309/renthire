package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.ClientReg;
import com.lumentech.renthire.entity.Owner;
import com.lumentech.renthire.entity.Property;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.ClientRegDto;
import com.lumentech.renthire.payload.OwnerDto;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.repository.OwnerRepository;
import com.lumentech.renthire.repository.PropertyRepository;
import com.lumentech.renthire.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final ModelMapper mapper;

    private final OwnerRepository ownerRepository;
    
    private final PropertyRepository propertyRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, ModelMapper mapper){
        this.mapper = mapper;
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
    }
    @Override
    public OwnerDto createOwner(OwnerDto dto) {
        long propertyId = dto.getProperty().getPropertyId();
        Optional<Property> property = propertyRepository.findById(propertyId);
        if(!property.isPresent()){
            throw new ResourceNotFoundException("Property Details", "propertyId", propertyId);
        }
        Property propertyDetail = property.get();

        Owner owner = mapToEntity(dto);
        owner.setProperty(propertyDetail);
        Owner saveOwner = ownerRepository.save(owner);
        OwnerDto ownerDto = mapToDto(saveOwner);
        return ownerDto;
    }

    @Override
    public OwnerDto findOwnerById(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
        );
        return mapToDto(owner);
    }

    @Override
    public OwnerDto updateOwner(OwnerDto dto, long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
        );
        owner.setPhone(dto.getPhone());
        Owner update = ownerRepository.save(owner);
        return mapToDto(update);
    }

    @Override
    public void deleteOwnerDetail(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Owner", "id", id)
        );
        ownerRepository.delete(owner);
    }

    @Override
    public List<OwnerDto> findAllOwner() {
        List<Owner> all = ownerRepository.findAll();
        return all.stream().map(owner -> mapToDto(owner)).collect(Collectors.toList());
    }

    @Override
    public PageResponse getAllOwner(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Owner> owners = ownerRepository.findAll(pageable);

        List<Owner> listOfOwner = owners.getContent();
        List<OwnerDto> listOfOwnerDto = listOfOwner.stream().map(owner
                -> mapToDto(owner)).collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setOwnerContent(listOfOwnerDto);
        pageResponse.setPageNo(owners.getNumber());
        pageResponse.setPageSize(owners.getSize());
        pageResponse.setTotalPages(owners.getTotalPages());
        pageResponse.setTotalElements(owners.getTotalElements());
        pageResponse.setLast(owners.isLast());
        return pageResponse;

    }

    public OwnerDto mapToDto(Owner owner) {
        OwnerDto dto = mapper.map(owner, OwnerDto.class);
        return dto;
    }

    public Owner mapToEntity(OwnerDto dto) {
        Owner owner = mapper.map(dto, Owner.class);
        return owner;
    }
}
