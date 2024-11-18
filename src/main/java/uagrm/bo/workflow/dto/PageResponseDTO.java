package uagrm.bo.workflow.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<T> {
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalItems;

    public PageResponseDTO(List<T> content, int currentPage, int pageSize, long totalItems) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }
}
