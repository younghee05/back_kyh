package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqCartListDto;
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

    @GetMapping("/cart/order") // 장바구니에서 체크 한 상품만 결제창으로
    public ResponseEntity<?> getCartOrder(ReqCartListDto dto) {
        return ResponseEntity.ok().body(orderService.getCartOrderList(dto));
    }

    @PutMapping("/order")
    public ResponseEntity<?> modify( ) {
        return ResponseEntity.ok().body(null);
    }
}
