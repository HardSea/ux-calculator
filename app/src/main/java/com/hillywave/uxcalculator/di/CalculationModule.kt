package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Part
import com.hillywave.uxcalculator.domain.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class CalculationModule {

	@Binds
	abstract fun bindPart(part: Part.Base): Part

	@Binds
	@ViewModelScoped
	abstract fun bindMainRepository(baseMainRepository: MainRepository.Base): MainRepository

	@Binds
	abstract fun bindMainController(baseMainController: MainController.Base): MainController

	@Binds
	abstract fun bindHistoryController(baseHistoryController: HistoryController.Base): HistoryController

	@Binds
	abstract fun bindFlowController(baseFlowController: FlowController.Base): FlowController

	@Binds
	abstract fun bindHandleOperation(handleOperationUseCase: HandleOperationUseCase): HandleOperation
}
