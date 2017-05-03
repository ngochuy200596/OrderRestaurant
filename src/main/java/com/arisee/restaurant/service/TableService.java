package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.table.Table;
import com.arisee.restaurant.model.table.TableForm;
import com.arisee.restaurant.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    private final TableRepository tableRepository;

    @Autowired
    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<com.arisee.restaurant.model.table.Table> getAll() {
        final List<com.arisee.restaurant.model.table.Table> rs = new ArrayList<>();
        this.tableRepository.findAll().forEach(table -> {
            rs.add(table.toTable());
        });
        return rs;
    }

    public Table create(TableForm tableForm){
      Table table = new Table();
      table.setName(tableForm.getName());
      table.setLocation(tableForm.getLocation());
      return this.tableRepository.save(table);
    }
    public Optional<Table> getById(BigInteger id){
        return this.tableRepository.getById(id);
    }
    public void delete(BigInteger id){
        getById(id).ifPresent(tableRepository::delete);
    }
    public Optional<Table> update(BigInteger id, TableForm tableForm){
        return getById(id).map(table ->{
            table.setName(tableForm.getName());
            table.setLocation(tableForm.getLocation());
            return this.tableRepository.save(table);
        });
    }
}
