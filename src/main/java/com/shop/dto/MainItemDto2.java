package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * The type Main item dto 2.
 */
@Getter@Setter
public class MainItemDto2 {

    private Long id;

    private String itemNm;

    private String itemDetail;

    private String imgUrl;

    private Integer price;

    //추가한 부분 24-01-09
    private Long memberId;
    //추가한 부분 24-01-09

    //추가한 부분 24-01-22
    private LocalDateTime regTime;

    /**
     * Instantiates a new Main item dto.
     *
     * @param id         the id
     * @param itemNm     the item nm
     * @param itemDetail the item detail
     * @param imgUrl     the img url
     * @param price      the price
     * @param memberId   the member id
     * @param regTime    the reg time
     */
//추가한 부분 24-01-22
    //
    @QueryProjection
    public MainItemDto2(Long id, String itemNm, String itemDetail, String imgUrl,Integer price, Long memberId, LocalDateTime regTime){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
        //추가한 부분 24-01-09
        this.memberId = memberId;
        //추가한 부분 24-01-09
        this.regTime = regTime;
    }
}
