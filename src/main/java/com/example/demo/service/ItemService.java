package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(ItemDto itDTO){
        Item item = new Item();
        item.setItemName(itDTO.getItemName());
        item.setPrice(itDTO.getPrice());
        item.setStockQuantity(itDTO.getStockQuantity());
        itemRepository.save(item);
    }

    public List<Item> findItems(int id){
        return itemRepository.findAll();
    }

    public void updateItemName(int id, String newName) {
        Optional<Item> FinditemId = itemRepository.findById(id);

        FinditemId.ifPresent(item -> {
            item.setItemName(newName);
            itemRepository.save(item);
        });
    }

    public List<Item> getAllMembers() {
        System.out.print("studentList:" + itemRepository.findAll());
        return itemRepository.findAll();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();

    }

    public void addStockItem(String itemName) {
        Optional<Item> itemOption = itemRepository.findByItemName(itemName);
        if(itemOption.isPresent()){
            Item item = itemOption.get();
            item.addStock(1);
            itemRepository.save(item);
            System.out.println("해당 상품 재고 증가"+itemName + "현재 재고" + item.getStockQuantity());
        }else{
            System.out.println("해당 아이템 이름 틀림");
        }
    }
}
