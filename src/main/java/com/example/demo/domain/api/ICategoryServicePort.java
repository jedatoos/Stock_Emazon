package com.example.demo.domain.api;


import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.PageResult;
import com.example.demo.domain.util.PageResultUtil;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    PageResult<Category> getAllCategoriesPaginated(PageResultUtil pageResultUtil);
   /* diseñado para dividir las categorías en páginas y devolver la información
    correspondiente a una página específica. Esto significa que en lugar de
    devolver todas las categorías de una sola vez, el método te entrega solo
    las categorías que corresponden a la página solicitada, junto con detalles
    como el número total de páginas, el tamaño de la página, el número de la página
    actual, entre otros.*/
}
