package com.shop.controller;

import com.shop.service.ItemService;
import com.shop.vo.ItemSearchVo;
import com.shop.vo.MainItemVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping(value = "/")
    public String main(ItemSearchVo itemSearchVo, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        Page<MainItemVo> items = itemService.getMainItemPage(itemSearchVo, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchVo", itemSearchVo);
        model.addAttribute("maxPage", 5);

        return "main";
    }

}