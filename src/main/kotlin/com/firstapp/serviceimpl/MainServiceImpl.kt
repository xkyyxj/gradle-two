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
		//ά���������Ĳ���ʱ����Ϣ
		consumeType.insert_time = Date()
		
		var (returnCode, returnObj) = repository.insert(consumeType)
		
		returnObj = returnObj as? ConsumeType?
		if(returnCode == Repository.SUCCESS)
			return CommonReturnInfo(MainService.SUCCESS,"����ɹ�", returnObj?.consumetype_id)
		else
			return CommonReturnInfo(MainService.FAILED,"����ʧ��")
	}
	
	override fun requireTypeInfo(userId : Long) : Array<ConsumeType>? {
		var parameter = HashMap<String,Long>()
		parameter.put("0",userId)
		var returnList = repository.query("from ConsumeType consume_type where consume_type.user_id=?0",parameter,ConsumeType::class)
		if(returnList != null && returnList.size > 0){
			//��������д��뻹�ǻص����뱨��δ����������ת�����ľ���
			//@Suppress("UNECHECKED_CAST")
			//return returnList.toTypedArray() as Array<ConsumeType>
			//ͨ��һ�ι���returnList���е�ÿһ��Ԫ���ж��Ƿ���ConsumeTypeʵ�����ҷ���(����)
			return returnList.filterIsInstance(ConsumeType::class.java).toTypedArray()
		}
		
		return null
	}
	
}