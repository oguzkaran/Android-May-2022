package org.csystem.app.startanother.shareddatalib

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class StringOperationProvider @Inject constructor(@ActivityContext private val activityContext : Context) {
    fun valuesStr() : Array<String>
    {
        return StringOperation.values()
            .map { it.resource }
            .map { activityContext.resources.getString(it) }
            .toTypedArray()
    }
}