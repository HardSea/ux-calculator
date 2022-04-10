package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.data.FlowRepository
import com.hillywave.uxcalculator.data.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

	@Provides
	fun provideFlowRepository(mainRepository: MainRepository): FlowRepository {
		return mainRepository
	}
}
