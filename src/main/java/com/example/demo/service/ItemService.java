package com.example.demo.service;

import com.example.demo.dto.ItemDto;
import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
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

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findItems(int id){
        return itemRepository.findById(id).orElse(null);
    }

    public void updateItemName(int id, String newName) {
        Optional<Item> finditemId = itemRepository.findById(id);
        ItemDto itemDto = new ItemDto();

        finditemId.ifPresent(item -> {
            int userId = item.getId();
            itemDto.setId(userId);
            itemDto.setItemName(newName);

            itemRepository.save(item);
        });
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> FindBooksBypageRequest(Pageable pageable) {

        return itemRepository.findAll(pageable).getContent();
    }
}
