package com.example.demo.domain.model;

import java.util.List;
//Ejecuta la orden
public class Pagination<T> {
    private List<T> content;//lista de elementos de la página actual
    private Long totalElements;//El número total de elementos disponibles en la consulta (no solo los que están en la página actual).
    private int totalPages;
    private int currentPage;// El número de la página actual.
    private boolean ascending;
    private boolean empty;

    public Pagination(boolean ascending, int currentPage, int totalPages, Long totalElements, List<T> content) {
        this.ascending = ascending;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.content = content;
        this.empty = content.isEmpty();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}

