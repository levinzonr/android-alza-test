package cz.levinzonr.spotistats.injection

import dagger.Module

@Module(includes = [RepositoryModule::class, DatabaseModule::class, RestModule::class])
class DataModule