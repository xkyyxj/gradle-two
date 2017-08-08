package com.firstapp.serviceimpl

import java.util.Date

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.firstapp.service.MainService
import com.firstapp.dao.Repository
import com.firstapp.entity.ConsumeType
import com.firstapp.service.CommonReturnInfo

@Component
class MainServiceImpl : MainService{
	
	@Autowired
	lateinit var repository : Repository
	
	override fun insertConsumeInfo() {
		
	}
	
	override fun insertConsumeType(consumeType : ConsumeType) : CommonReturnInfo {
		//维护消费类别的插入时间信息
		consumeType.insert_time = Date()
		
		var (returnCode, returnObj) = repository.insert(consumeType)
		
		returnObj = returnObj as? ConsumeType?
		if(returnCode == Repository.SUCCESS)
			return CommonReturnInfo(MainService.SUCCESS,"插入成功", returnObj?.consumetype_id)
		else
			return CommonReturnInfo(MainService.FAILED,"插入失败")
	}
	
	override fun requireTypeInfo(userId : Long) : Array<ConsumeType>? {
		var parameter = HashMap<String,Long>()
		parameter.put("0",userId)
		var returnList = repository.query("from ConsumeType consume_type where consume_type.user_id=?0",parameter,ConsumeType::class)
		if(returnList != null && returnList.size > 0){
			//下面的两行代码还是回到编译报“未经检查的类型转换”的警告
			//@Suppress("UNECHECKED_CAST")
			//return returnList.toTypedArray() as Array<ConsumeType>
			//通过一次过滤returnList当中的每一个元素判定是否是ConsumeType实例并且返回(可用)
			return returnList.filterIsInstance(ConsumeType::class.java).toTypedArray()
		}
		
		return null
	}
	
}