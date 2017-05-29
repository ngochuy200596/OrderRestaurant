package com.arisee.restaurant.controller;

import com.arisee.restaurant.domain.reserve.Reserve;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.reserve.ReserveForm;
import com.arisee.restaurant.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/reserves")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

//    @RequestMapping(method = RequestMethod.GET)
//    public List<com.arisee.restaurant.model.reserve.Reserve> getAll(){
//        return this.reserveService.getAll();
//    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reserve> getAll() {
        return this.reserveService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Reserve getById(@PathVariable("id") BigInteger id) {
        return reserveService.getById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.reserveService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reserve update(@PathVariable BigInteger id, @Valid @RequestBody ReserveForm reserveForm) {
        return reserveService.update(id, reserveForm).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Reserve insert(@Valid @RequestBody ReserveForm reserveForm) {
        return reserveService.create(reserveForm);
    }

    @RequestMapping(value = "tableId/{tableId}", method = RequestMethod.GET)
    public List<Reserve> findByTableId(@PathVariable BigInteger tableId) {
        return reserveService.findByTableId(tableId).orElseThrow(NotFoundException::new);
    }
}
