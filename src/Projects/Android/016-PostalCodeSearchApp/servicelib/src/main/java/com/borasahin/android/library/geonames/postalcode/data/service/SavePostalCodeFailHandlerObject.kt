package com.borasahin.android.library.geonames.postalcode.data.service

import com.karandev.util.data.service.DataServiceException

internal data class SavePostalCodeFailHandlerObject(var ex: DataServiceException, var failBlock: (DataServiceException) -> Unit)

