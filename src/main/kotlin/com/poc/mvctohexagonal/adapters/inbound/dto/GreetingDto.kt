package com.poc.mvctohexagonal.adapters.inbound.dto

data class GreetingRequest(val name: String, val message: String)

data class GreetingResponse(val id: Long?, val name: String, val message: String)

