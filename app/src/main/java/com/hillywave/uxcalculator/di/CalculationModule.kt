package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.NUMBER_LENGTH_LIMIT
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Part
import com.hillywave.uxcalculator.domain.BaseMainController
import com.hillywave.uxcalculator.domain.HistoryController
import com.hillywave.uxcalculator.domain.FlowController
import com.hillywave.uxcalculator.domain.core.MainController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CalculationModule {

    @Binds
    fun bindPart(part: Part.Base): Part

    @Binds
    @ViewModelScoped
    fun bindMainRepository(baseMainRepository: MainRepository.Base): MainRepository

    @Binds
    fun bindMainController(baseMainController: BaseMainController): MainController

    @Binds
    fun bindHistoryController(baseHistoryController: HistoryController.Base): HistoryController

    @Binds
    fun bindFlowController(baseFlowController: FlowController.Base): FlowController

    companion object {
        @Provides
        @NumberLengthLimit
        fun bindNumberLengthLimit(): Int = NUMBER_LENGTH_LIMIT
    }
}
