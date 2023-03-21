package com.example.demo.app

import android.app.Application
import android.content.Context
import com.example.demo.di.component.AppComponent
import com.example.demo.di.component.DaggerAppComponent
import com.example.demo.di.module.AppModule

class App : Application() {

    init {
        instance = this
    }

    companion object {

        var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

    }

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }
}