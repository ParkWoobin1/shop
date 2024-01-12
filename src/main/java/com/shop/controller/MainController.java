package com.shop.controller;


import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.dto.MainItemDto2;
import com.shop.entity.Member;
import com.shop.service.ItemService;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ItemService itemService;
    private final MemberService memberService;

    /**
     * Main string.
     *
     * @param itemSearchDto the item search dto
     * @param page          the page
     * @param model         the model
     * @param principal     the principal
     * @return the string
     */
    @GetMapping(value = "/main")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model, Principal principal){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
        //24-01-12 추가한부분
        String email = principal.getName();
        Member member = memberService.findMember(email);
        String role = member.getRole().toString();




            //Page<MainItemDto> items = itemService.getMainAdminItemPage(itemSearchDto, pageable,principal);

            Page<MainItemDto2> items = itemService.getMainAdminItemPage2(itemSearchDto, pageable,principal);

        //24-01-12 추가한부분


        //추가한부분24-01-09
        //Page<MainItemDto2> items = itemService.getMainAdminItemPage2(itemSearchDto, pageable,principal);
        //Page<MainItemDto> items = itemService.getMainAdminItemPage(itemSearchDto, pageable,principal);
        //추가한부분24-01-09
        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);
        return "main";
    }


    @GetMapping(value = "/")
    public String main(){

        return "/member/memberLoginForm";
    }
}
