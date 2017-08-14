package com.firstapp.service

import com.firstapp.entity.ConsumeType
import com.firstapp.entity.ConsumeContent

interface MainService {
	
	companion object {
		const val SUCCESS = 0
		const val FAILED = 1
	}
	
	fun insertConsumeInfo(consumeInfo : ConsumeContent) : CommonReturnInfo
	
	fun requireConsumeInfo(typeId : Long) : Array<ConsumeContent>?
	
	fun insertConsumeType(consumeType : ConsumeType) : CommonReturnInfo
	
	fun requireTypeInfo(userId : Long) : Array<ConsumeType>?
	
}