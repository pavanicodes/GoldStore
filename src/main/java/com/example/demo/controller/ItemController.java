package com.example.demo.controller;

import com.example.demo.model.ItemDTO;
import com.example.demo.model.ItemRequest;
import com.example.demo.service.ItemService;
import jakarta.websocket.server.PathParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * we used spring boot in the backend.
 *
 * Request will go from UI call first will come to controller then service and then to Repositotry layer and it will hit the database.
 * Response will from database to repository to service and controller then back UI
 *
 *
 *
 */

@RestController()
@RequestMapping(path = "/items")
public class ItemController {


    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     *
     *
     *
     */

    /**
     * this one is to fetch the available items in the store.
     * @return
     */
    @GetMapping
    public List<ItemDTO> listItems() {
        List<ItemDTO> itemDTOS = itemService.listItems(0, 0);
        System.out.println(itemDTOS);
        return itemDTOS;
    }

    @GetMapping(path = "/search")
    public List<ItemDTO> searchItems(@RequestParam int priceLower,
                                     @RequestParam int pricerHigher) {
        List<ItemDTO> itemDTOS = itemService.listItems(priceLower, pricerHigher);
        System.out.println(itemDTOS);
        return itemDTOS;
    }

    /**
     * this one is for creating item
     *
     * @param itemRequest
     * @return
     */
    @PostMapping
    public ItemDTO createItem(@RequestBody ItemRequest itemRequest) {
        ItemDTO item = itemService
            .createItem(itemRequest);
        System.out.println(item);
        return item;
    }


    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long itemId) {
        itemService
            .deleteItem(itemId);
    }

}
