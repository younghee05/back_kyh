package org.test.teamproject_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.teamproject_back.dto.request.ReqSearchSalesDto;
import org.test.teamproject_back.service.AdminSalesService;

@RequestMapping("/admin/sales")
@RestController
public class AdminSalesController {

    @Autowired
    private AdminSalesService adminSalesService;

    @GetMapping("/day") // 일 별 매출 목록 및 합
    public ResponseEntity<?> getSales(ReqSearchSalesDto dto) {
        return ResponseEntity.ok().body(adminSalesService.getSalesList(dto));
    }

    @GetMapping("/month") // 월 별 매출 목록 및 합
    public ResponseEntity<?> getMonthSales(@RequestParam String date) {
        return ResponseEntity.ok().body(adminSalesService.getMonthSalesList(date));
    }

    @GetMapping("/graph")
    public ResponseEntity<?> getGraphData() {
        return ResponseEntity.ok().body(adminSalesService.getGraphData());
    }


//    @GetMapping("/category")
//    public ResponseEntity<?> getCategorySales(@RequestParam String category) {
//    }

}
