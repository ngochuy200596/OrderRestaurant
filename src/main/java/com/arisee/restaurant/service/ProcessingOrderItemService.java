//package com.arisee.restaurant.service;
//
//import com.arisee.restaurant.domain.processingOrder.ProcessingOrderItem;
//import com.arisee.restaurant.model.processingOrder.ProcessingOrderItemForm;
//import com.arisee.restaurant.repository.ProcessingOrderItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProcessingOrderItemService {
//    private final ProcessingOrderItemRepository processingOrderItemRepository;
//    @Autowired
//    public ProcessingOrderItemService(ProcessingOrderItemRepository processingOrderItemRepository) {
//        this.processingOrderItemRepository = processingOrderItemRepository;
//    }
//
//    public List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> getAll(){
//        final List<com.arisee.restaurant.model.processingOrder.ProcessingOrderItem> rs = new ArrayList<>();
//        this.processingOrderItemRepository.findAll().forEach(processingOrderItem -> {
//            rs.add(processingOrderItem.toProcessingItem());
//
//        });
//        return rs;
//    }
//
//    public ProcessingOrderItem create(ProcessingOrderItemForm processingOrderItemForm){
//        ProcessingOrderItem processingOrderItem = new ProcessingOrderItem();
//        processingOrderItem.setDescription(processingOrderItemForm.getDescription());
//        processingOrderItem.setQuantity(processingOrderItemForm.getQuantity());
//        processingOrderItem.setStatus(processingOrderItemForm.getStatus());
//        return this.processingOrderItemRepository.save(processingOrderItem);
//    }
//
//    public Optional<ProcessingOrderItem> getById(Integer id){
//        return this.processingOrderItemRepository.getById(id);
//    }
//
//    public void delete(Integer id){
//        getById(id).ifPresent(processingOrderItemRepository::delete);
//    }
//   public Optional<ProcessingOrderItem> update(Integer id, ProcessingOrderItemForm processingOrderItemForm){
//        return getById(id).map(processingOrderItem -> {
//            processingOrderItem.setDescription(processingOrderItemForm.getDescription());
//            processingOrderItem.setQuantity(processingOrderItemForm.getQuantity());
//            processingOrderItem.setStatus(processingOrderItemForm.getStatus());
//            return this.processingOrderItemRepository.save(processingOrderItem);
//        });
//
//   }
//
//}
