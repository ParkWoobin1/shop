package com.shop.repository;

import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StoreRepositoryImplTest {

    @Autowired
    private StoreRepository storeRepository;

    @Test
    @DisplayName("자산실사 테스트")
    public void realityTest(){
        Item item;

    }

}