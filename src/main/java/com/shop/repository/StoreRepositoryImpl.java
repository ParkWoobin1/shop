package com.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.dto.ItemDto;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.util.List;

public class StoreRepositoryImpl implements StoreRepository{

    private JPAQueryFactory queryFactory;
    private EntityManager entityManager;
    @Override
    public List<ItemDto> itemRealty(ItemDto itemDto, Principal principal) {

        String itemRealty = "SELECT * FROM ITEM I WHERE I.MEMBER_ID = MEMBER_ID";
        List<ItemDto> result = entityManager.createQuery(itemRealty, ItemDto.class)
                .setParameter("member_id", itemDto)
                .getResultList();
        return result;
    }
}
