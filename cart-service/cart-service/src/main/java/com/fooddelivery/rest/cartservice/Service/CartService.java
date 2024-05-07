package com.fooddelivery.rest.cartservice.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.ResponseSpec;

import com.fooddelivery.rest.cartservice.Configuration.Constant;
import com.fooddelivery.rest.cartservice.Exception.ApiException;
import com.fooddelivery.rest.cartservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.cartservice.Model.Cart;
import com.fooddelivery.rest.cartservice.Model.CartItem;
import com.fooddelivery.rest.cartservice.Model.FoodItemVariant;
import com.fooddelivery.rest.cartservice.Model.FoodItems;
import com.fooddelivery.rest.cartservice.Repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private RestClient restClient;

    
    public Cart createCart(String userId) throws ApiException
    {
        if(getCartByUserId(userId)!=null)
        {
            throw new ApiException("cart is already exist with userId "+userId);
        }
        List<CartItem> item = new ArrayList<CartItem>();
        Cart cart = new Cart();
        cart.setId(userId);
        cart.setUserId(userId);
        cart.setCartitems(item);
        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(String userId)
    {
       return cartRepository.findCartByUserId(userId);
    }

    //add item into cart

    public Cart addProductIntoCart(String userId, String productId, String quantity) throws ResourceNotFoundException 
    {
        
        Cart cart = cartRepository.findCartByUserId(userId);
       
        List<CartItem> item = cart.getCartitems();

        CartItem cartItem = new CartItem();
       
        FoodItems foodItems = restClient.get().uri("/items/id/{productId}",productId).retrieve().body(FoodItems.class);

        //set cart items
        cartItem.setCartItemId(UUID.randomUUID().toString());
        cartItem.setFoodItemId(foodItems.getId());
        cartItem.setName(foodItems.getName());
        cartItem.setDiscount(foodItems.getDiscount());

        if(!Arrays.asList(Constant.quantity).contains(quantity))
        {
            throw new ResourceNotFoundException("Item not found with quantity: "+quantity);
        }
            
        for(FoodItemVariant val : foodItems.getVariant())
        {
            if(val.getQuantity().equals(quantity))
            {
                cartItem.setPrice(val.getPrice());
                cartItem.setSpecialPrice(val.getSpecialPrice());
                cartItem.setQuantity(val.getQuantity());
            }
            
        }
        
        //set cart
        item.add(cartItem);
        cart.setCartitems(item);
    
        Cart newCart =cartRepository.save(cart);

        return setCartPrice(newCart);
    }
    
    //remove cart item from cart

    public Cart deleteCartItem(String userId, String cartItemId)
    {
        Cart cart = getCartByUserId(userId);

        List<CartItem> cartItem = cart.getCartitems();

        for(CartItem item : cartItem)
        {
            if(item.getCartItemId().equals(cartItemId))
            {
                cartItem.remove(item);
                break;
            }
        }
        cart.setCartitems(cartItem);

        Cart newCart = cartRepository.save(cart);

        return setCartPrice(newCart);
    }

    //set cart price
    public Cart setCartPrice(Cart cart)
    {
        Double totalPrice = 0.0;
        Double totalDiscount = 0.0;

        List<CartItem> cartItem = cart.getCartitems();

        if(cartItem == null)
        {
            cart.setTotalDiscount(0.0);
            cart.setTotalPrice(0.0);

            return cartRepository.save(cart);
        }

        for(CartItem item : cartItem )
        {
            totalPrice += item.getSpecialPrice();
            totalDiscount += item.getDiscount();
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscount(totalDiscount);

       return cartRepository.save(cart);
    }


}
