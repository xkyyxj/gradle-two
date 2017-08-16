package com.firstapp.serviceimpl

import java.util.Date

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.firstapp.service.MainService
import com.firstapp.dao.Repository
import com.firstapp.entity.ConsumeType
import com.firstapp.service.CommonReturnInfo
import com.firstapp.entity.ConsumeContent

@Component
class MainServiceImpl : MainService{
	
	//val logger : Logger = LoggerFactory.getLogger(MainServiceImpl::class.java)
	
	val logger : Logger = LoggerFactory.getILoggerFactory().getLogger("fileLog")
	
	@Autowired
	lateinit var repository : Repository
	
	override fun insertConsumeInfo(consumeInfo : ConsumeContent) : CommonReturnInfo {
		var (returnCode, returnObj) = repository.insert(consumeInfo)
		
		returnObj = returnObj as ConsumeContent?
		if(returnCode == Repository.SUCCESS) {
			logger.info("Insert ConsumeContent successfully, consume_type_id : {}", consumeInfo.consume_type_id)
			return CommonReturnInfo(MainService.SUCCESS,"插入成功", returnObj?.consume_type_id)
		}
		else {
			logger.info("Insert ConsumeContent failed, consume_type_id : {}", returnObj?.consume_type_id)
			return CommonReturnInfo(MainService.FAILED,"插入失败")
		}
	}
	
	override fun requireConsumeInfo(typeId : Long) : Array<ConsumeContent>?{
		var hql = "from ConsumeContent consume_content where consume_content.consume_type_id=?0"
		var parameters = hashMapOf("0" to typeId)
		
		var returnList = repository.query(hql, parameters, ConsumeContent::class)
		
		if(returnList != null && returnList.size > 0){
			return returnList.filterIsInstance(ConsumeContent::class.java).toTypedArray()
		}
		
		return null
	}
	
	override fun insertConsumeType(consumeType : ConsumeType) : CommonReturnInfo {
		//维护消费类别的插入时间信息
		consumeType.insert_time = Date()
		
		var (returnCode, returnObj) = repository.insert(consumeType)
		
		returnObj = returnObj as? ConsumeType?
		if(returnCode == Repository.SUCCESS) {
			logger.info("User(ID : {}) insert ConsumeType successfully", consumeType.user_id)
			return CommonReturnInfo(MainService.SUCCESS,"插入成功", returnObj?.consumetype_id)
		}
		else {
			logger.info("User(ID : {}) insert ConsumeType failed", consumeType.user_id)
			return CommonReturnInfo(MainService.FAILED,"插入失败")
		}
	}
	
	override fun requireTypeInfo(userId : Long) : Array<ConsumeType>? {
		var parameter = HashMap<String,Long>()
		parameter.put("0",userId)
		logger.info("User(ID : {}) require ConsumeTypeInfo", userId)
		var returnList = repository.query("from ConsumeType consume_type where consume_type.user_id=?0",parameter,ConsumeType::class)
		if(returnList != null && returnList.size > 0){
			//下面的两行代码还是回到编译报“未经检查的类型转换”的警告
			//@Suppress("UNECHECKED_CAST")
			//return returnList.toTypedArray() as Array<ConsumeType>
			//通过一次过滤returnList当中的每一个元素判定是否是ConsumeType实例并且返回(可用)
			return returnList.filterIsInstance(ConsumeType::class.java).toTypedArray()
		}
		
		return emptyArray<ConsumeType>()
	}
	
}