package com.firstapp.service

import com.firstapp.entity.ConsumeType

interface MainService {
	
	companion object {
		const val SUCCESS = 0
		const val FAILED = 1
	}
	
	fun insertConsumeInfo()
	
	fun insertConsumeType(consumeType : ConsumeType) : CommonReturnInfo
	
	fun requireTypeInfo(userId : Long) : Array<ConsumeType>?
	
}