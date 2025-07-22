package com.poc.mvctohexagonal.domain.port

import com.poc.mvctohexagonal.domain.model.Greeting

// Porta inbound: define operações do domínio
interface GreetingServicePort {
    fun createGreeting(name: String, message: String): Greeting
    fun getGreeting(id: Long): Greeting?
    fun listGreetings(): List<Greeting>
    fun deleteGreeting(id: Long)
}

