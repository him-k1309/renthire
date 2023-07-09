package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Owner;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.OwnerDto;
import com.lumentech.renthire.repository.OwnerRepository;
import com.lumentech.renthire.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }
    @Override
    public OwnerDto createOwner(OwnerDto dto) {
        Owner owner = mapToEntity(dto);
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

    public OwnerDto mapToDto(Owner saveOwner) {
        OwnerDto dto = new OwnerDto();
        dto.setOwnerId(saveOwner.getOwnerId());
        dto.setOwnerName(saveOwner.getOwnerName());
        dto.setPhone(saveOwner.getPhone());
        return dto;
    }

    public Owner mapToEntity(OwnerDto dto) {
        Owner owner = new Owner();
        owner.setOwnerName(dto.getOwnerName());
        owner.setPhone(dto.getPhone());
        return owner;
    }
}
