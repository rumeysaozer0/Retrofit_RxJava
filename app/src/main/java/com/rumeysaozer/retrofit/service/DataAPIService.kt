package com.rumeysaozer.retrofit.service

import com.rumeysaozer.retrofit.model.DataItem
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DataAPIService {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DataAPI::class.java)
    fun getData(): Single<List<DataItem>>{
        return api.getDatas()
    }
}