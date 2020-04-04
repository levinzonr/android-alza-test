package cz.levinzonr.spotistats

import cz.levinzonr.spotistats.domain.AppConfig
import javax.inject.Inject

class AppConfigImpl @Inject constructor(): AppConfig {
    override val apiUrl: String
        get() = BuildConfig.API_URL
}