package com.poc.mvctohexagonal.adapters.outbound

import com.poc.mvctohexagonal.domain.model.Greeting
import com.poc.mvctohexagonal.domain.port.GreetingRepositoryPort
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

// Implementação em memória da porta outbound
@Component
class InMemoryGreetingRepository : GreetingRepositoryPort {
    private val greetings = ConcurrentHashMap<Long, Greeting>()
    private val idGenerator = AtomicLong(1)

    override fun save(greeting: Greeting): Greeting {
        val id = greeting.id ?: idGenerator.getAndIncrement()
        val saved = Greeting(id, greeting.name, greeting.message)
        greetings[id] = saved
        return saved
    }

    override fun findById(id: Long): Greeting? = greetings[id]

    override fun findAll(): List<Greeting> = greetings.values.toList()

    override fun deleteById(id: Long) {
        greetings.remove(id)
    }
}
