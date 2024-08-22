package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.jpa.ItemRepository;
import com.example.demo.model.ItemDTO;
import com.example.demo.model.ItemRequest;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> listItems(int priceLower, int priceHigher) {
        List<Item> itemList = itemRepository.findAll();
        System.out.println(itemList);

        List<ItemDTO> collect = itemList
            .stream()
            .map(item -> {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setCode(item.getCode());
                itemDTO.setId(item.getId());
                itemDTO.setPrice(item.getPrice());
                itemDTO.setDescription(item.getDescription());
                itemDTO.setName(item.getName());
                return itemDTO;
            }).collect(Collectors.toList());

        if (priceLower == 0 && priceHigher == 0) {
            return collect;
        }

        if (priceLower > 0 || priceHigher > 0) {
            collect = collect
                .stream()
                .filter(item -> item.getPrice() >= priceLower && item.getPrice() <= priceHigher)
                .collect(Collectors.toList());
        }
        System.out.println(collect);

        return collect;
    }

    public ItemDTO createItem(ItemRequest itemRequest) {
        System.out.println(itemRequest);

        Item item = new Item();
        item.setCode(itemRequest.getCode());
        item.setDescription(itemRequest.getDescription());
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());

        Item savedItem = itemRepository
            .save(item);

        System.out.println(savedItem);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setCode(item.getCode());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setName(item.getName());

        return itemDTO;
    }

    public void deleteItem(Long itemId) {

        Item referenceById = itemRepository
            .getReferenceById(itemId);

        itemRepository
            .delete(referenceById);
    }
}
