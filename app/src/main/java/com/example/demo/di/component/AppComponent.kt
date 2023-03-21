package com.example.demo.di.component

import com.example.demo.di.module.AppModule
import com.example.demo.network.RemoteDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(remoteDataSource: RemoteDataSource)

}