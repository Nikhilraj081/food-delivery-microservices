package com.fooddelivery.rest.foodmenuservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.foodmenuservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.foodmenuservice.Model.FoodItems;
import com.fooddelivery.rest.foodmenuservice.Service.FoodItemService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems() {
        List<FoodItems> items = foodItemService.getAllItems();

        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getItemsById(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<FoodItems> item = foodItemService.getItemById(id);

        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveItems(@Valid @RequestBody FoodItems items) {
        FoodItems item = foodItemService.saveItems(items);

        return ResponseEntity.created(null).body(item);
    }

    @PostMapping("/id/{id}/update")
    public ResponseEntity<?> updateItems(@Valid @PathVariable("id") String id, @RequestBody FoodItems item)
            throws ResourceNotFoundException {
        FoodItems foodItem = foodItemService.updateItems(id, item);

        return ResponseEntity.accepted().body(foodItem);
    }

}
