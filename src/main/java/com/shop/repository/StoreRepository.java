package com.shop.repository;

import com.shop.dto.ItemDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface StoreRepository {

    List<ItemDto> itemRealty(ItemDto itemDto, Principal principal );

}
