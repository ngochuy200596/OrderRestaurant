package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.reserve.Reserve;
import com.arisee.restaurant.model.reserve.ReserveForm;
import com.arisee.restaurant.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {
    private final ReserveRepository reserveRepository;

    @Autowired
    public ReserveService(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    public List<com.arisee.restaurant.model.reserve.Reserve> getAll(){
        final List<com.arisee.restaurant.model.reserve.Reserve> rs = new ArrayList<>();
         this.reserveRepository.findAll().forEach(reserve -> {
             rs.add(reserve.toReserve());
         });
        return rs;
    }

    public Optional<Reserve> getById(BigInteger id){
        return this.reserveRepository.getById(id);
    }
    public void delete(BigInteger id){
        getById(id).ifPresent(reserveRepository::delete);
    }

    public Optional<Reserve> update(BigInteger id, ReserveForm reserveForm){
        return getById(id).map(reserve -> {
            reserve.setCustomerName(reserveForm.getCustomerName());
            reserve.setPhone(reserveForm.getPhone());
            reserve.setTableId(reserveForm.getTableId());
            reserve.setScheduleOn(reserveForm.getScheduleOn());
            return this.reserveRepository.save(reserve);
        });
    }

    public Reserve create(ReserveForm reserveForm){
        Reserve reserve = new Reserve();
        reserve.setCustomerName(reserveForm.getCustomerName());
        reserve.setPhone(reserveForm.getPhone());
        reserve.setTableId(reserveForm.getTableId());
        reserve.setScheduleOn(reserveForm.getScheduleOn());
        reserve.setCreatedDate(LocalDateTime.now());
        return this.reserveRepository.save(reserve);
    }
}
