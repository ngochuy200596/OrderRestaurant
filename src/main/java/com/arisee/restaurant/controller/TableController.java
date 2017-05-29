package com.arisee.restaurant.controller;


import com.arisee.restaurant.domain.table.Table;
import com.arisee.restaurant.exception.NotFoundException;
import com.arisee.restaurant.model.table.TableForm;
import com.arisee.restaurant.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TableController {
    @Autowired
    private TableService tableService;

    @RequestMapping(method = RequestMethod.GET)
    public List<com.arisee.restaurant.model.table.Table> getAllTable(){
        return this.tableService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Table getById(@PathVariable("id") BigInteger id){
        return this.tableService.getById(id).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.tableService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Table update(@PathVariable BigInteger id, @Valid @RequestBody TableForm tableForm) {
        return tableService.update(id, tableForm).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}/status/{status}",method = RequestMethod.POST)
    public Table setStatus(@PathVariable BigInteger id, @PathVariable Integer status){
        return tableService.setStatus(id,status)
                .orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Table insert(@Valid  @RequestBody TableForm tableForm) {
        return tableService.create(tableForm);
    }
}
