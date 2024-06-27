package com.fooddelivery.rest.restaurantsservice.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.rest.restaurantsservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItems;
import com.fooddelivery.rest.restaurantsservice.Payloads.ItemResponse;
import com.fooddelivery.rest.restaurantsservice.Service.FoodItemService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems() throws IOException {
        List<ItemResponse> items = foodItemService.getAllItems();

        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getItemsByCategory(@PathVariable("category") String category) throws IOException {

        List<ItemResponse> items = foodItemService.getItemsByCategory(category);

        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchItems(@PathVariable("keyword") String keyword) throws IOException {

        List<ItemResponse> items = foodItemService.searchItems(keyword);

        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getItemsById(@PathVariable("id") String id) throws ResourceNotFoundException {
        Optional<FoodItems> item = foodItemService.getItemById(id);

        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveItems(@Valid @RequestParam("items") String items,
            @RequestParam("image") MultipartFile image) throws IOException {

        FoodItems foodItems = null;
        try {
            foodItems = mapper.readValue(items, FoodItems.class);
        } catch (JsonMappingException e) {

            e.printStackTrace();
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }

        FoodItems item = foodItemService.saveItems(foodItems, image);

        return ResponseEntity.created(null).body(item);
    }

    @PutMapping("/id/{id}/update")
    public ResponseEntity<?> updateItems(@Valid @PathVariable("id") String id, @RequestBody FoodItems item)
            throws ResourceNotFoundException {
        FoodItems foodItem = foodItemService.updateItems(id, item);

        return ResponseEntity.accepted().body(foodItem);
    }

    @GetMapping("/image/{imageName}")
    public HttpServletResponse getImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {
        InputStream resource = foodItemService.getImage(imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
        return response;
    }

}
