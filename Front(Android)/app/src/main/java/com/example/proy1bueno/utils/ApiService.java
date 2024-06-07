package com.example.proy1bueno.utils;

import com.example.proy1bueno.addCompra.data.DataAddCompra;
import com.example.proy1bueno.addProduct.data.DataProductAdd;
import com.example.proy1bueno.categoriesFilter.data.DataCategoriesFilter;
import com.example.proy1bueno.historicalPurchases.data.DataCompra;
import com.example.proy1bueno.listProductsUser.data.DataProductLst;
import com.example.proy1bueno.loginUser.data.DataUser;
import com.example.proy1bueno.lstBetterRates.data.DataLstBetterRates;
import com.example.proy1bueno.productFile.data.DataProductFile;
import com.example.proy1bueno.rate.data.DataRate;
import com.example.proy1bueno.userFilter.data.DataUserFilter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @GET("MyServlet")
    Call<DataUser> getDataLoginUser(@Query("ACTION") String action, @Query("username") String username, @Query("password") String password);

//    @GET("MyServlet")
//      Call<DataProduct> getDataProductList(@Query("ACTION") String action);

    @GET("MyServlet")
    Call<DataProductLst> getDataProductList(@Query("ACTION") String action, @Query("idUser")int idUser);

    @GET("MyServlet")
    Call<DataProductAdd> getDataAddProduct( @Query("ACTION") String action,
                                            @Query("idProducto") int idProducto,
                                            @Query("nombreProducto") String nombreProducto,
                                            @Query("precioProducto") Double precioProducto,
                                            @Query("marcaProducto") String marcaProducto,
                                            @Query("fechaSubidaProducto") String fechaSubidaProducto,
                                            @Query("descripcionProducto") String descripcionProducto,
                                            @Query("imagenProducto") String imagenProducto,
                                            @Query("idUser") int idUser
    );

    /*
    le paso el cation (USER.FILTER) + el tipo de filtro que quiero
    pt.3 filtro por mayor numero de ventas.
    filter= userMostSells
     */
    @GET("MyServlet")
    Call<DataUserFilter> getUserFilter(@Query("ACTION")String action, @Query("FILTER") String filter);

//    @GET("MyServlet")
//    Call<DataRate> getAddValoracion(@Query("ACTION")String action);
    @GET("MyServlet")
    Call<DataRate> getAddValoracionComplete(@Query("ACTION")String action,
                                            @Query("idUser")int idUser,
                                            @Query("idProducto")int idProducto,
                                            @Query("numEstrellas")float numEstrellas);

    @GET("MyServlet")
    Call<DataLstBetterRates>getLstBetterRates(@Query("ACTION")String action, @Query("FILTER")String filter);


    @GET("MyServlet")
    Call<DataCategoriesFilter>getCategories(@Query("ACTION")String action, @Query("nombreCategoria")String categoria);

    @GET("MyServlet")
    Call<DataProductFile> getProductFile(@Query("ACTION")String action, @Query("idProduct")int idProduct);


    @GET("MyServlet")
    Call<DataCompra>getHistoricalPurchases(@Query("ACTION")String action, @Query("idUser")int idUser);

    @GET("MyServlet")
    Call<DataAddCompra> getDataAddCompra(@Query("ACTION") String action,
                                         @Query("idUser") int idUser,
                                         @Query("idProduct") int idProduct);

//    @GET("MyServlet")
//    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action);
//
//    @GET("MyServlet")
//    Call<DataListUsers> getDataListUsers(@Query("ACTION") String action, @Query("FILTER") String filter);


//    @GET("MyServlet")
//    Call<DataMovies> getDataMovies(@Query("ACTION") String action);

//    @GET("MyServlet")
//    Call<DataMovies> getDataMovies2(@Query("ACTION") String action);
    
}
