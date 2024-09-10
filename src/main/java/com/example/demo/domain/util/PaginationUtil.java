package com.example.demo.domain.util;
//Recibe orden
public class PaginationUtil {
    private int pageSize;//El número de elementos por página.
    private int pageNumber;// El número de la página que se desea obtener
    private String nameFilter;//Un filtro de búsqueda o criterio para filtrar los elementos. Puede ser un nombre
    private boolean ascending;//Un valor booleano que indica si los elementos deben ordenarse en orden ascendente.

    public PaginationUtil() {
    }

    public PaginationUtil(int pageSize, int pageNumber, String nameFilter, boolean ascending) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber -1;
        this.nameFilter = nameFilter;
        this.ascending = ascending;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
