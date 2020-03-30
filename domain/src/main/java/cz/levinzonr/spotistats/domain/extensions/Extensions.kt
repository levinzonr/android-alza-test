package cz.levinzonr.spotistats.domain.extensions

import cz.levinzonr.spotistats.domain.interactors.Interactor
import cz.levinzonr.spotistats.domain.interactors.CompleteResult
import cz.levinzonr.spotistats.domain.interactors.Fail
import cz.levinzonr.spotistats.domain.interactors.Success
import cz.levinzonr.spotistats.domain.models.DataModel
import cz.levinzonr.spotistats.domain.models.DomainModel

inline fun <T> T.guard(block: T.() -> Unit): T {
    if (this == null) block(); return this
}
