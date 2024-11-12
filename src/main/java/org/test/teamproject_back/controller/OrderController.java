package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.dto.request.ReqCartListDto;
import org.test.teamproject_back.dto.request.ReqOrderDto;
import org.test.teamproject_back.dto.request.ReqProductOrderDto;
import org.test.teamproject_back.service.OrderService;

@RestController
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(ReqProductOrderDto dto) {
        return ResponseEntity.ok().body(orderService.getOrderList(dto));
    }

    @PostMapping("/order")
    public ResponseEntity<?> addOrder(@RequestBody ReqOrderDto dto) {
        System.out.println(dto);
        orderService.addOrder(dto);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/cart/order") // 장바구니에서 체크 한 상품만 결제창으로
    public ResponseEntity<?> getCartOrder(ReqCartListDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(orderService.getCartOrderList(dto));
    }

    @GetMapping("/order/record")
    public ResponseEntity<?> getOrderRecord() {
        return ResponseEntity.ok().body(orderService.getOrderRecord());
    }


}
