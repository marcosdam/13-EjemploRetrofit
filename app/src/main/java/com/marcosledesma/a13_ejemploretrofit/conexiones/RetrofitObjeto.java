package com.marcosledesma.a13_ejemploretrofit.conexiones;

import com.marcosledesma.a13_ejemploretrofit.configuraciones.Parametros;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObjeto {

    public static Retrofit getConexion(){
        return new Retrofit.Builder()
                .baseUrl(Parametros.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
