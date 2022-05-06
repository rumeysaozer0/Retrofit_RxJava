package com.rumeysaozer.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rumeysaozer.retrofit.model.DataItem
import com.rumeysaozer.retrofit.service.DataAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val dataAPIService = DataAPIService()
    private val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDataFromAPI()
    }
    fun getDataFromAPI(){
        disposable.add(
            dataAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DataItem>>(){
                    override fun onSuccess(t: List<DataItem>) {
                        val myStringBuilder = StringBuilder()
                        for (data in t ){
                            myStringBuilder.append(data.title)
                            myStringBuilder.append("\n")
                        }
                        findViewById<TextView>(R.id.text).text = myStringBuilder
                    }

                    override fun onError(e: Throwable) {
                       e.printStackTrace()
                    }

                })
        )
    }
}