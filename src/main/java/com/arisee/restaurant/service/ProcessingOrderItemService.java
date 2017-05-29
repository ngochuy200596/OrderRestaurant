package com.arisee.restaurant.service;

import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemPK;
import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItemStatus;
import com.arisee.restaurant.model.dish.Dish;
import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
import com.arisee.restaurant.repository.ProcessingOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by NGOCHUY on 5/18/2017.
 */
@Service
public class ProcessingOrderItemService {
    private final ProcessingOrderItemRepository processingOrderItemRepository;
    private final TableProcessingOrderService tableProcessingOrderService;

    @Autowired
    public ProcessingOrderItemService(ProcessingOrderItemRepository processingOrderItemRepository, TableProcessingOrderService tableProcessingOrderService) {
        this.processingOrderItemRepository = processingOrderItemRepository;
        this.tableProcessingOrderService = tableProcessingOrderService;
    }

    public List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> getAll() {
        final List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> rs = new ArrayList<>();
        this.processingOrderItemRepository.findAll().forEach(processingOrderItem -> {
            rs.add(processingOrderItem.toProcessingOrderItem());
        });
        return rs;
    }

    public Optional<ProcessingOrderItem> setStatusItem(BigInteger tableId, Integer id, Integer status) {
        return tableProcessingOrderService.getById(tableId).flatMap(tableProcessingOrder -> {
            ProcessingOrderItemPK processingOrderItemPK = new ProcessingOrderItemPK();
            processingOrderItemPK.setId(id);
            processingOrderItemPK.setTableProcessingOrder(tableProcessingOrder);

            return Optional.ofNullable(processingOrderItemRepository.findOne(processingOrderItemPK));
        }).map(processingOrderItem -> {
            processingOrderItem.setStatus(ProcessingOrderItemStatus.values()[status]);
            return processingOrderItemRepository.save(processingOrderItem);
        });
    }

//    public Optional<ProcessingOrderItem> findById(Integer id, BigInteger tableId){
//        return this.processingOrderItemRepository.findById(id,tableId);
//    }

//    public ProcessingOrderItem create(ProcessingOrderItemForm processingOrderItemForm){
//        ProcessingOrderItem item = new ProcessingOrderItem();
//        item.setId(processingOrderItemForm.getId());
//        item.setStatus(ProcessingOrderItemStatus.AVAILABLE);
//        item.setDescription(processingOrderItemForm.getDescription());
//        item.setQuantity(processingOrderItemForm.getQuantity());
//        item.setTableId(processingOrderItemForm.getTableId());
//        item.setDish(processingOrderItemForm.getDish());
//        return this.processingOrderItemRepository.save(item);
//    }
}
