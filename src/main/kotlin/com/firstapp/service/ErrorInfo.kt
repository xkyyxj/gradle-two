package com.firstapp.service

class ErrorInfo (var returnCode : Int, var errorInfo : String?,
				 var userId : Long? = null, var userName : String? = null,
				 var password : String? = null) {
}