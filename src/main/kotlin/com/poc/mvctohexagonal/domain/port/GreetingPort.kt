package com.poc.mvctohexagonal.domain.port

import com.poc.mvctohexagonal.domain.model.Greeting

// Porta outbound: abstrai o repositório
interface GreetingRepositoryPort {
    fun save(greeting: Greeting): Greeting
    fun findById(id: Long): Greeting?
    fun findAll(): List<Greeting>
    fun deleteById(id: Long)
}
