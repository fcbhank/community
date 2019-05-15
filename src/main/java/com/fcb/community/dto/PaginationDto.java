package com.fcb.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于分页界面的bean
 * Created by hank on 19-5-15
 */
@Data
public class PaginationDto {
    List<QuestionDto> questionDtos;
    // 分页界面中的4个按钮
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    // 下方分页组件会显示的页码
    private List<Integer> showPages = new ArrayList<>();
    // 按照size分的总共页数
    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer currentPage, Integer size) {
        // 确定总共页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        // 对currentPage做范围约束
        if (currentPage < 1)
            currentPage = 1;
        if (currentPage > totalPage)
            currentPage = totalPage;
        // 更新currentPage
        this.currentPage = currentPage;

        //添加当前页到页面显示的页码中
        showPages.add(currentPage);
        // 页面一共显示7个页码(以当前页为中心，两侧各添加3个页码)，依次添加剩余页码
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                // 从中间往左边插入
                showPages.add(0, currentPage - i);
            }

            if (currentPage + i <= totalPage) {
                showPages.add(currentPage + i);
            }
        }

        // bool默认是false，是否显示上一页按钮
        if (currentPage != 1)
            showPrevious = true;
        // 是否显示下一页按钮
        if (currentPage != totalPage)
            showNext = true;
        // 是否显示第一页按钮
        if (!showPages.contains(1))
            showFirstPage = true;
        // 是否显示最后一页按钮
        if (!showPages.contains(totalPage))
            showEndPage = true;

    }
}
