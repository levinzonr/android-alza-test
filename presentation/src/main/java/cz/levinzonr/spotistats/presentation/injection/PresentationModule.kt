package cz.levinzonr.spotistats.presentation.injection

import cz.levinzonr.spotistats.presentation.injection.modules.ActivityModule
import cz.levinzonr.spotistats.presentation.injection.modules.FragmentsModule
import cz.levinzonr.spotistats.presentation.injection.modules.ViewModelBuilder
import cz.levinzonr.spotistats.presentation.injection.modules.ViewModelModule
import dagger.Module

@Module(includes = [
    ViewModelBuilder::class,
    ViewModelModule::class,
    FragmentsModule::class,
    ActivityModule::class])
class PresentationModule