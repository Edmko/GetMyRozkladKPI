package com.example.getmyrozkladkpi.network

import com.example.getmyrozkladkpi.data.Lessons
import com.example.getmyrozkladkpi.data.week.Week
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://api.rozklad.org.ua/v2/"


interface RozkladAPI {
    @GET("groups/{group}/{call}")
    fun getProperties(@Path("group") groupName: String, @Path("call") call: String): Call<Lessons>
    @GET("weeks")
    fun getWeekNumber() : Call<Week>

}
object RozkladApiObj{
    val retrofitService : RozkladAPI by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).client(client).build()
        retrofit.create(RozkladAPI::class.java)
    }
}