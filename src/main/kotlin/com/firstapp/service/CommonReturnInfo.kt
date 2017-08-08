package com.firstapp.service

data class CommonReturnInfo (var returnCode : Int, var returnInfo : String,
							 var returnValue : Any? = null)