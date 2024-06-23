package com.fooddelivery.rest.cartservice.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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

    public Cart createCart(String userId) throws ApiException {
        if (cartRepository.findCartByUserId(userId) != null) {
            throw new ApiException("cart is already exist with userId " + userId);
        }
        List<CartItem> item = new ArrayList<CartItem>();
        Cart cart = new Cart();
        cart.setId(userId);
        cart.setUserId(userId);
        cart.setCartitems(item);

        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(String userId) throws ApiException {

        if (cartRepository.findCartByUserId(userId) == null) {
            createCart(userId);
        }

        Cart newCart = cartRepository.findCartByUserId(userId);
       
        return newCart;
    }

    // add item into cart

    public Cart addProductIntoCart(String userId, String productId, String quantity, String token)
            throws ResourceNotFoundException, ApiException {

        if (cartRepository.findCartByUserId(userId) == null) {
            createCart(userId);
        }

        Cart cart = cartRepository.findCartByUserId(userId);

        List<CartItem> item = cart.getCartitems();

        if (item != null) {
            for (CartItem cartItem : item) {

                if (cartItem.getFoodItemId().equals(productId) && cartItem.getQuantity().equals(quantity)) {

                    return updateCartItem(cartItem.getNumOfItem() + 1, userId, cartItem.getCartItemId());
                }

            }
        }

        CartItem cartItem = new CartItem();

        FoodItems foodItems = restClient.get().uri("restaurants-service/items/id/{productId}", productId)
                .header("Authorization", token)
                .retrieve().body(FoodItems.class);

        // set cart items
        cartItem.setCartItemId(UUID.randomUUID().toString());
        cartItem.setFoodItemId(foodItems.getId());
        cartItem.setName(foodItems.getName());
        cartItem.setDiscount(foodItems.getDiscount());
        cartItem.setNumOfItem(Constant.DEFAULT_NO);

        if (!Arrays.asList(Constant.quantity).contains(quantity)) {
            throw new ResourceNotFoundException("Item not found with quantity: " + quantity);
        }

        for (FoodItemVariant val : foodItems.getVariant()) {
            if (val.getQuantity().equals(quantity)) {
                cartItem.setPrice(val.getPrice());
                cartItem.setSpecialPrice(val.getSpecialPrice());
                cartItem.setQuantity(val.getQuantity());
            }

        }

        // set cart
        item.add(cartItem);
        cart.setCartitems(item);

        Cart newCart = cartRepository.save(cart);

        return setCartPrice(newCart);
    }

    public Cart updateCartItem(int noOfItem, String userId, String cartItemId) throws ApiException {
        if (cartRepository.findCartByUserId(userId) == null) {
            createCart(userId);
        }

        Cart cart = getCartByUserId(userId);

        List<CartItem> cartItem = cart.getCartitems();

        for (CartItem item : cartItem) {

            if (item.getCartItemId().equals(cartItemId)) {
                item.setNumOfItem(noOfItem);
            }
        }

        Cart newCart = cartRepository.save(cart);
        return setCartPrice(newCart);
    }

    // remove cart item from cart

    public Cart deleteCartItem(String userId, String cartItemId) throws ApiException {

        if (cartRepository.findCartByUserId(userId) == null) {
            createCart(userId);
        }
        Cart cart = getCartByUserId(userId);

        List<CartItem> cartItem = cart.getCartitems();

        for (CartItem item : cartItem) {
            if (item.getCartItemId().equals(cartItemId)) {
                cartItem.remove(item);
                break;
            }
        }
        cart.setCartitems(cartItem);

        Cart newCart = cartRepository.save(cart);

        return setCartPrice(newCart);
    }

    // Remove all item from cart
    public Cart removeAllCartItem(String cartId) {

        Cart cart = cartRepository.findById(cartId).get();

        cart.getCartitems().clear();
        cart.setTotalPrice(Constant.DEFAULT_PRICE);
        cart.setTotalDiscount(Constant.DEFAULT_PRICE);
        cart.setDeliveryFee(Constant.DEFAULT_PRICE);

        return cartRepository.save(cart);

    }

    // set cart price
    public Cart setCartPrice(Cart cart) {
        Double totalPrice = 0.0;
        Double totalDiscount = 0.0;

        List<CartItem> cartItem = cart.getCartitems();

        if (cartItem == null) {
            cart.setTotalDiscount(0.0);
            cart.setTotalPrice(0.0);

            return cartRepository.save(cart);
        }

        for (CartItem item : cartItem) {
            totalPrice += item.getSpecialPrice() * item.getNumOfItem();
            totalDiscount += item.getDiscount() * item.getNumOfItem();
        }

        if (totalPrice < 200) {
            cart.setDeliveryFee(Constant.DELIVERY_FEE);
            totalPrice = totalPrice + Constant.DELIVERY_FEE;
        } else {
            cart.setDeliveryFee(Constant.DEFAULT_PRICE);
        }

        cart.setTotalPrice(totalPrice);
        cart.setTotalDiscount(totalDiscount);
        if (totalPrice < Constant.FREE_DELIVERY_LIMIT) {
            cart.setDeliveryFee(Constant.DELIVERY_FEE);
        }

        return cartRepository.save(cart);
    }

}
