package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.service.OrderService;

@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(@RequestParam Long id) {
        return ResponseEntity.ok().body(orderService.getOrderList(id));
    }

    @GetMapping("/cart/order")
    public ResponseEntity<?> getCartOrder() {
        return ResponseEntity.ok().body(orderService.getCartOrderList());
    }
}
