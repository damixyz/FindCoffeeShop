package com.damixyz.findcoffee.di

import com.damixyz.data.data.Item
import com.damixyz.data.mapper.CoffeeVenueMapper
import com.damixyz.data.mapper.Mapper
import com.damixyz.data.repositories.FourSquareRepository
import com.damixyz.data.repositories.FourSquareRepositoryImp
import com.damixyz.data.source.DataSource
import com.damixyz.data.source.RemoteDataSource
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.usecases.GetCoffeeShopInfoUseCase
import com.damixyz.usecases.GetCoffeeShopInfoUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ActivityComponent::class, ViewModelComponent::class)
@Module
abstract class BuilderModule {

    // use cases
    @Binds
    abstract fun bindGetCoffeeShopInfoUseCase(getCoffeeShopInfoUseCaseImp: GetCoffeeShopInfoUseCaseImp):
            GetCoffeeShopInfoUseCase

    // Repositories
    @Binds
    abstract fun bindFourSquareRepository(fourSquareRepositoryImp: FourSquareRepositoryImp):
            FourSquareRepository

    // Data Source
    @Binds
    abstract fun bindDataSource(remoteDataSource: RemoteDataSource):
            DataSource

    // Mapper
    @Binds
    abstract fun bindCoffeeVenueMapper(coffeeVenueMapper: CoffeeVenueMapper):
            Mapper<Item, CoffeeShopInfo>
}