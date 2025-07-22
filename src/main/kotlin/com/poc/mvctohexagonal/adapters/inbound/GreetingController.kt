package com.poc.mvctohexagonal.adapters.inbound

import com.poc.mvctohexagonal.domain.model.Greeting
import com.poc.mvctohexagonal.domain.port.GreetingServicePort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.poc.mvctohexagonal.adapters.inbound.dto.GreetingRequest
import com.poc.mvctohexagonal.adapters.inbound.dto.GreetingResponse
import com.poc.mvctohexagonal.adapters.inbound.mapper.GreetingMapper

@RestController
@RequestMapping("/greetings")
class GreetingController(
    private val greetingService: GreetingServicePort,
    private val greetingMapper: GreetingMapper
) {
    @PostMapping
    fun create(@RequestBody request: GreetingRequest): ResponseEntity<GreetingResponse> {
        val greeting = greetingService.createGreeting(request.name, request.message)
        return ResponseEntity.ok(greetingMapper.toResponse(greeting))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<GreetingResponse> {
        val greeting = greetingService.getGreeting(id)
        return greeting?.let { ResponseEntity.ok(greetingMapper.toResponse(it)) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping
    fun list(): List<GreetingResponse> = greetingMapper.toResponseList(greetingService.listGreetings())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        greetingService.deleteGreeting(id)
        return ResponseEntity.noContent().build()
    }
}
