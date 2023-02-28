package com.borasahin.android.library.geonames.postalcode.data.service

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.IPostalCodeMapper
import com.erbaris.android.library.geonames.postalcode.data.dal.PostalCodeAppHelper
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCodeInfo
import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class PostalCodeAppService @Inject constructor() {
    @Inject
    lateinit var postalCodeAppHelper: PostalCodeAppHelper

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    @Inject
    lateinit var threadPool: ExecutorService

    @Inject
    lateinit var handler: Handler

    private fun savePostalCodeThreadCallBack(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, resultBlock: (Boolean) -> Unit,
                                             failBlock: (DataServiceException) -> Unit)
    {
        try {
            if (postalCodeSaveDTOs.isEmpty()) {
                resultBlock(false)
                return
            }
            val code = postalCodeSaveDTOs[0].code;
            val list = postalCodeSaveDTOs.map { postalCodeMapper.toPostalCode(it) }.toList();
            val ho = SavePostalCodeResultHandlerObject(postalCodeAppHelper.savePostalCode(PostalCodeInfo(code), list), resultBlock)

            handler.sendMessage(handler.obtainMessage(1, ho))
        }
        catch (ex: RepositoryException) {
            val ho = SavePostalCodeFailHandlerObject(DataServiceException("PostalCodeAppService.savePostalCode", ex.cause), failBlock)

            handler.sendMessage(handler.obtainMessage(2, ho))
        }
    }

    private fun findPostalCodesByCodeThreadCallback(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                                      failBlock: (DataServiceException) -> Unit)
    {
        try {
            resultBlock(postalCodeAppHelper.findPostalCodesByCode(code).map { postalCodeMapper.toPostalCodeDTO(it) }.toMutableList())
        }
        catch (ex: RepositoryException) {
            failBlock(DataServiceException("PostalCodeAppService.savePostalCode", ex.cause))
        }
    }

    fun savePostalCode(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, resultBlock: (Boolean) -> Unit,
                       failBlock: (DataServiceException) -> Unit)
    {
        threadPool.execute{savePostalCodeThreadCallBack(postalCodeSaveDTOs, resultBlock, failBlock)}
    }

    fun findPostalCodesByCode(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                              failBlock: (DataServiceException) -> Unit)
    {

        threadPool.execute{findPostalCodesByCodeThreadCallback(code, resultBlock, failBlock)}
    }
}