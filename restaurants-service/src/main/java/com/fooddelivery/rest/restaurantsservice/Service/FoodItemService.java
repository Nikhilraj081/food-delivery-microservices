package com.fooddelivery.rest.restaurantsservice.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;

import org.apache.commons.lang.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fooddelivery.rest.restaurantsservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.restaurantsservice.Model.FoodImage;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItemVariant;
import com.fooddelivery.rest.restaurantsservice.Model.FoodItems;
import com.fooddelivery.rest.restaurantsservice.Payloads.ItemResponse;
import com.fooddelivery.rest.restaurantsservice.Repository.FoodItemRepository;

import org.springframework.core.io.Resource;
import org.apache.commons.io.IOUtils;


@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${image.path}")
    private String path;

    public List<ItemResponse> getAllItems() throws IOException {

        Type listType = new TypeToken<List<ItemResponse>>(){}.getType();

        List<FoodItems> newItems = foodItemRepository.findAll();

        List<ItemResponse> itemResponses = modelMapper.map(foodItemRepository.findAll(), listType);


    //image conversion

    Iterator<FoodItems> i1 = newItems.iterator();
    Iterator<ItemResponse> i2 = itemResponses.iterator();

    while(i1.hasNext() && i2.hasNext())
    {
        String fullPath = path+File.separator+i1.next().getImage().getImage();

        // Resource resource = new ClassPathResource(fullPath);
        InputStream resource = new FileInputStream(fullPath);
        byte[] imageBytes = IOUtils.toByteArray(resource);
        String imageDataBase64 = Base64.getEncoder().encodeToString(imageBytes);

        i2.next().setImage(imageDataBase64);
    }

    
        return itemResponses;
    }

    public Optional<FoodItems> getItemById(String id) throws ResourceNotFoundException {
        Optional<FoodItems> items = foodItemRepository.findById(id);
        if (items == null) {
            throw new ResourceNotFoundException("item not found with id: " + id);
        }
        return items;
    }

    public FoodItems saveItems(FoodItems items, MultipartFile image) throws IOException {
        
        List<FoodItemVariant> variant = items.getVariant();

         // get file name
         String originalFileName = image.getOriginalFilename();

         // get extention
         String extention = com.google.common.io.Files.getFileExtension(originalFileName);
 
         // Generate random filename
         String fileName = RandomStringUtils.randomAlphanumeric(10) + "." + extention;
 
         // get path
         String filePath = path + File.separator + fileName;
 
         // create folder
         File file = new File(path);
 
         if (!file.exists()) {
             file.mkdir();
         }
 
         Files.copy(image.getInputStream(), Paths.get(filePath));

         FoodImage foodImage = new FoodImage();
         foodImage.setTitle(image.getOriginalFilename());
         foodImage.setImage(fileName);

        for (FoodItemVariant val : variant) {

            val.setSpecialPrice(val.getPrice() - items.getDiscount());
        }

        items.setVariant(variant);
        items.setImage(foodImage);

        return foodItemRepository.save(items);
    }

    public FoodItems updateItems(String id, FoodItems items) throws ResourceNotFoundException {
        FoodItems foodItems = getItemById(id).get();

        if (foodItems == null) {
            throw new ResourceNotFoundException("item not found with id: " + id);
        }

        foodItems.setAbout(items.getAbout());
        foodItems.setCategory(items.getCategory());
        foodItems.setDiscount(items.getDiscount());
        foodItems.setName(items.getName());
        foodItems.setReview(items.getReview());
        foodItems.setType(items.getType());
        foodItems.setVariant(items.getVariant());

        List<FoodItemVariant> variant = items.getVariant();

        for (FoodItemVariant val : variant) {

            val.setSpecialPrice(val.getPrice() - items.getDiscount());
        }

        foodItems.setVariant(variant);
        return foodItemRepository.save(foodItems);
    }

    public InputStream getImage(String imageName) throws FileNotFoundException
    {
        String fullPath = path+File.separator+imageName;

        InputStream inputStream = new FileInputStream(fullPath);

        return inputStream;
    }
}
