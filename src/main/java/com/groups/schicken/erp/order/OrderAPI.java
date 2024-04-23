package com.groups.schicken.erp.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/orders")
public class OrderAPI {
    private final OrderService orderService;
}
