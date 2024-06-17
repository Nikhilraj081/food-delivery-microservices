package com.fooddelivery.rest.cartservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.rest.cartservice.Exception.ApiException;
import com.fooddelivery.rest.cartservice.Exception.ResourceNotFoundException;
import com.fooddelivery.rest.cartservice.Model.Cart;
import com.fooddelivery.rest.cartservice.Service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createCart(@PathVariable String userId) throws ApiException {
        Cart cart = cartService.createCart(userId);

        return ResponseEntity.ok().body(cart);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findCartByUserId(@PathVariable String userId) throws ApiException {
        Cart cart = cartService.getCartByUserId(userId);

        return ResponseEntity.ok().body(cart);
    }

    @PostMapping("/add/{userId}/{itemId}/quantity/{quantity}")
    public ResponseEntity<?> addItemIntoCart(@PathVariable String userId, @PathVariable String itemId,
            @PathVariable String quantity, @RequestHeader("Authorization") String token)
            throws ResourceNotFoundException, ApiException {
        Cart cart = cartService.addProductIntoCart(userId, itemId, quantity, token);

        return ResponseEntity.created(null).body(cart);
    }

    @PutMapping("/update/cartItem/{itemId}/user/{userId}/{noOfItem}")
    public ResponseEntity<?> updateCart(@PathVariable("itemId") String itemId, @PathVariable("userId") String userId, @PathVariable("noOfItem") int noOfItem) throws ApiException
    {
        Cart cart = cartService.updateCartItem(noOfItem, userId, itemId);
        return ResponseEntity.created(null).body(cart);
    }


    @DeleteMapping("/delete/userId/{userId}/itemId/{itemId}")
    public ResponseEntity<?> removeCartItem(@PathVariable String userId, @PathVariable String itemId) throws ApiException {
        Cart cart = cartService.deleteCartItem(userId, itemId);

        return ResponseEntity.accepted().body(cart);
    }

    @DeleteMapping("/{cartId}/delete/allItem")
    public ResponseEntity<?> removeAllCartItem(@PathVariable String cartId) {
        Cart cart = cartService.removeAllCartItem(cartId);

        return ResponseEntity.accepted().body(cart);
    }

}
