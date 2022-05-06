package com.rumeysaozer.retrofit.service
import com.rumeysaozer.retrofit.model.DataItem
import io.reactivex.Single
import retrofit2.http.GET
interface DataAPI {
@GET("posts")
fun getDatas(): Single<List<DataItem>>
}