package com.spring.auth.shared.infrastructure.dto.output;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author diegotobalina created on 03/08/2020
 */
@Getter
@ToString
public class PagedListOutputDto {

    private final List<?> content;
    private final long total_elements;
    private final int number_elements;
    private final int total_pages;

    public PagedListOutputDto(List<?> content, long total_elements, int total_pages) {
        this.content = content;
        this.total_elements = total_elements;
        this.number_elements = content.size();
        this.total_pages = total_pages;
    }
}
