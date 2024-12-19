package com.mario.omiechallenge.data.di

import com.mario.omiechallenge.data.datasource.LocalDataSource
import com.mario.omiechallenge.data.repository.RepositoryImpl
import com.mario.omiechallenge.domain.Repository
import org.koin.dsl.module

val dataModule = module {

    single {
        LocalDataSource()
    }

    single<Repository> {
        RepositoryImpl(get())
    }
}