package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Part
import com.hillywave.uxcalculator.domain.MainController
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CalculationModule {

	@Binds
	abstract fun bindPart(part: Part.Base): Part

	@Binds
	abstract fun bindMainRepository(baseMainRepository: MainRepository.Base): MainRepository

	@Binds
	abstract fun bindMainController(baseMainController: MainController.Base): MainController
}