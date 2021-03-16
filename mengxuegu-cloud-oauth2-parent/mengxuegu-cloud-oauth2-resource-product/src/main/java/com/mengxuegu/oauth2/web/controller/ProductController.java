package com.mengxuegu.oauth2.web.controller;

import com.mengxuegu.base.result.MengxueguResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品资源
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('product')")
    public MengxueguResult list() {
        List<String> list = new ArrayList<>();
        list.add("眼镜");
        list.add("格子衬衣");
        list.add("双肩包");
        return MengxueguResult.ok(list);
    }
}
