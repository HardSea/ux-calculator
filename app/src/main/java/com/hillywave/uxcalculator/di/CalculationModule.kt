package com.hillywave.uxcalculator.di

import com.hillywave.uxcalculator.NUMBER_LENGTH_LIMIT
import com.hillywave.uxcalculator.data.BaseMainRepository
import com.hillywave.uxcalculator.data.NumberPart
import com.hillywave.uxcalculator.data.core.MainRepository
import com.hillywave.uxcalculator.data.core.Part
import com.hillywave.uxcalculator.domain.BaseHistoryController
import com.hillywave.uxcalculator.domain.BaseMainController
import com.hillywave.uxcalculator.domain.core.HistoryController
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
    fun bindPart(part: NumberPart): Part

    @Binds
    @ViewModelScoped
    fun bindMainRepository(baseMainRepository: BaseMainRepository): MainRepository

    @Binds
    fun bindMainController(baseMainController: BaseMainController): MainController

    @Binds
    fun bindHistoryController(baseHistoryController: BaseHistoryController): HistoryController

    companion object {
        @Provides
        @NumberLengthLimit
        fun provideNumberLengthLimit(): Int = NUMBER_LENGTH_LIMIT
    }
}
