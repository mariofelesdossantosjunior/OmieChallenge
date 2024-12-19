package com.mario.omiechallenge.di


import com.mario.omiechallenge.data.di.dataModule
import com.mario.omiechallenge.domain.di.domainModule
import com.mario.omiechallenge.presentation.di.presentationModule

val appModule = listOf(
    dataModule,
    domainModule,
    presentationModule
)
