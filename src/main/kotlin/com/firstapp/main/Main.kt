package com.firstapp.main

import com.main.java.JavaMain
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Main {
	
	fun test() {
		what(Main::class)
		var intArray : IntArray = intArrayOf(1,2,3)
		val asc:Array<String> = Array(5, { i -> (i * i).toString() })
		JavaMain.testArray(intArray)
		JavaMain.ha123(asc)
		var tempString : String = "asdfasdf"
		var tempString2 : String = "asdfasdf"
		JavaMain.hao123(*asc)
		JavaMain.hao123(tempString,tempString2)
		//����һ���ǻᱨ���
		//what2(Main::class)
		//����JavaMain��һ��Java�࣬����˵
		JavaMain.testClass(Main::class)
	}
		
	fun what(one : Any) = println("newone")
	
	fun what2(obj : java.lang.Object){
		println("wjhat2")
	}
}

fun main(args : Array<String>) {
	//var obj = Main::class.java
	//if(obj is java.lang.Object)
	//fuck!!�������ҵ����������Ľ����ʽ���ɱ������������봫��һ���������*����
	SpringApplication.run(Main::class.java, *args);
}