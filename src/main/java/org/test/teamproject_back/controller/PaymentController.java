package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.teamproject_back.service.PaymentService;

@RestController
@RequestMapping("/user/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public ResponseEntity<?> getPaymentNum (@RequestParam (required = false) Long orderId) {
        System.out.println(orderId);
        return ResponseEntity.ok().body(paymentService.getPaymentNum(orderId));
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<?> modifyStatus (@PathVariable String paymentId) {
        paymentService.modifyStatus(paymentId);
        return ResponseEntity.ok().body(true);
    }
}
