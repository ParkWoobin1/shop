package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainItemDto {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    //추가한 부분 24-01-09
    //private Long memberId;
    //추가한 부분 24-01-09

    /**
     * Instantiates a new Main item dto.
     *
     * @param id         the id
     * @param itemNm     the item nm
     * @param itemDetail the item detail
     * @param imgUrl     the img url
     * @param price      the price
     * @param memberId   the member id
     */
//
    @QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl,Integer price){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
        //추가한 부분 24-01-09
        //this.memberId = memberId;
        //추가한 부분 24-01-09
    }
}
