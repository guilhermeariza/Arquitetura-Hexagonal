package com.poc.mvctohexagonal.domain.service

import com.poc.mvctohexagonal.domain.model.Greeting
import com.poc.mvctohexagonal.domain.port.GreetingRepositoryPort
import com.poc.mvctohexagonal.domain.port.GreetingServicePort

// Serviço de domínio, implementa a porta inbound e depende da outbound
class GreetingService(
    private val greetingRepositoryPort: GreetingRepositoryPort
) : GreetingServicePort {
    override fun createGreeting(name: String, message: String): Greeting {
        val greeting = Greeting(id = null, name = name, message = message)
        return greetingRepositoryPort.save(greeting)
    }

    override fun getGreeting(id: Long): Greeting? = greetingRepositoryPort.findById(id)

    override fun listGreetings(): List<Greeting> = greetingRepositoryPort.findAll()

    override fun deleteGreeting(id: Long) = greetingRepositoryPort.deleteById(id)
}
