package com.firstapp.main

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class Server {
	
	val count : AtomicLong = AtomicLong()
	
	@RequestMapping("/greeting") fun greeting(@RequestParam(value="name", defaultValue="World") name : String) : Greeting
		= Greeting(count.incrementAndGet(),"world! $name")

}