package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.data.core.FlowRepository
import com.hillywave.uxcalculator.data.core.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    fun bindFlowRepository(mainRepository: MainRepository): FlowRepository
}
