package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.*;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import com.shop.entity.QItemImg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery){

        if(StringUtils.equals("itemNm", searchBy)){
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return QItem.item.createdBy.like("%" + searchQuery + "%");
        }

        return null;
    }
    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){
            dateTime = dateTime.minusMonths(6);
        }

        return QItem.item.regTime.after(dateTime);
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QueryResults<Item> results = queryFactory
                .selectFrom(QItem.item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression itemNmLike(String searchQuery){
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto> results;
        results = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price

                                )
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                //추가한부분 24-01-05
                //.where(item.member1.id.eq(itemSearchDto.getMemberId()))
                //추가한부분 24-01-05
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainItemDto2> getMainUserItemPage(ItemSearchDto itemSearchDto, Pageable pageable, Principal principal) {
        QItem item = QItem.item;

        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto2> results;
        results = queryFactory
                .select(
                        new QMainItemDto2(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price,
                                item.member1.id,

                                item.regTime)
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                //추가한부분 24-01-05
                .where(item.member1.id.eq(itemSearchDto.getMemberId()))
                //추가한부분 24-01-05
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto2> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public Page<MainItemDto2> getMainUserItemRealPage(ItemSearchDto itemSearchDto, Pageable pageable, Principal principal) {
        QItem item = QItem.item;

        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto2> results;
        results = queryFactory
                .select(
                        new QMainItemDto2(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price,
                                item.member1.id,
                                item.regTime

                        )
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                //추가한부분 24-01-05
                .where(item.member1.id.eq(itemSearchDto.getMemberId()))
                //추가한부분 24-01-05
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<MainItemDto2> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }


    @Override
    public List<MainItemDto2> getItemRealPicture(ItemSearchDto itemSearchDto) {
        QItem item = QItem.item;

        QItemImg itemImg = QItemImg.itemImg;

        QueryResults<MainItemDto2> results;
        results = queryFactory
                .select(
                        new QMainItemDto2(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price,
                                item.member1.id,
                                item.regTime

                        )
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                //추가한부분 24-01-05
                .where(item.member1.id.eq(itemSearchDto.getMemberId()))
                //추가한부분 24-01-05
                .orderBy(item.id.desc())
                .fetchResults();

        List<MainItemDto2> content = results.getResults();
        long total = results.getTotal();
        return  content;
    }
}
