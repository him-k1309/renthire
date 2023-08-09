package com.lumentech.renthire.service.impl;

import com.lumentech.renthire.entity.Rent;
import com.lumentech.renthire.exception.ResourceNotFoundException;
import com.lumentech.renthire.payload.PageResponse;
import com.lumentech.renthire.payload.RentDto;
import com.lumentech.renthire.repository.RentRepository;
import com.lumentech.renthire.service.RentService;
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
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public RentDto createNewService(RentDto rentDto) {
        Rent rent = mapToEntity(rentDto);
        Rent saveRent = rentRepository.save(rent);
        return mapToDto(saveRent);
    }

    @Override
    public RentDto getRentDetailById(long id) {
        Rent rent = rentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Rent Detail", "id", id)
        );
        return mapToDto(rent) ;
    }

    @Override
    public List<RentDto> getAllRentDetails() {
        List<Rent> allRent = rentRepository.findAll();
        List<RentDto> rentDto = allRent.stream().map(rent -> mapToDto(rent)).collect(Collectors.toList());
        return rentDto;
    }

    @Override
    public PageResponse getAllRentDetailsByPagination(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Rent> rentPage = rentRepository.findAll(pageable);

        List<Rent> content = rentPage.getContent();
        List<RentDto> contentList = content.stream()
                .map(rent -> mapToDto(rent))
                .collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setRentContent(contentList);
        pageResponse.setPageNo(rentPage.getNumber());
        pageResponse.setPageSize(rentPage.getSize());
        pageResponse.setTotalPages(rentPage.getTotalPages());
        pageResponse.setTotalElements(rentPage.getTotalElements());
        pageResponse.setLast(rentPage.isLast());
        return pageResponse;
    }

    @Override
    public RentDto updateRent(long id, RentDto rentDto) {
        Rent rent = rentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale Details", "id", id)
        );
        rent.setRentStartDate(rentDto.getRentStartDate());
        rent.setRentEndDate(rentDto.getRentEndDate());
        rent.setRentPrice(rentDto.getRentPrice());
        Rent updateRent = rentRepository.save(rent);
        return mapToDto(updateRent);
    }

    @Override
    public void deleteRentById(long id) {
        Rent rent = rentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Sale Details", "id", id)
        );
        rentRepository.deleteById(id);
    }

    private Rent mapToEntity(RentDto rentDTO) {
        Rent rent = mapper.map(rentDTO, Rent.class);
        return rent;
    }

    private RentDto mapToDto(Rent rent) {
        RentDto rentDTO = mapper.map(rent, RentDto.class);
        return rentDTO;
    }
}
