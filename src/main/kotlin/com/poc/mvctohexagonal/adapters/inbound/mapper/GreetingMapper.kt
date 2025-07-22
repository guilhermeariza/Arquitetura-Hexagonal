package com.poc.mvctohexagonal.adapters.inbound.mapper

import com.poc.mvctohexagonal.domain.model.Greeting
import com.poc.mvctohexagonal.adapters.inbound.dto.GreetingRequest
import com.poc.mvctohexagonal.adapters.inbound.dto.GreetingResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface GreetingMapper {
    fun toDomain(request: GreetingRequest): Greeting
    fun toResponse(greeting: Greeting): GreetingResponse
    fun toResponseList(greetings: List<Greeting>): List<GreetingResponse> = greetings.map { toResponse(it) }

    companion object {
        val INSTANCE: GreetingMapper = Mappers.getMapper(GreetingMapper::class.java)
    }
}

