package com.poc.mvctohexagonal.domain.service

import com.poc.mvctohexagonal.domain.model.Greeting
import com.poc.mvctohexagonal.domain.port.GreetingPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GreetingServiceTest {
    private lateinit var greetingPort: GreetingPort
    private lateinit var greetingService: GreetingService

    @BeforeEach
    fun setUp() {
        greetingPort = InMemoryGreetingPort()
        greetingService = GreetingService(greetingPort)
    }

    @Test
    fun `deve criar e buscar saudação`() {
        val created = greetingService.createGreeting("João", "Olá!")
        val found = greetingService.getGreeting(created.id!!)
        assertNotNull(found)
        assertEquals("João", found?.name)
        assertEquals("Olá!", found?.message)
    }

    @Test
    fun `deve listar saudações`() {
        greetingService.createGreeting("A", "Oi")
        greetingService.createGreeting("B", "Hello")
        val all = greetingService.listGreetings()
        assertEquals(2, all.size)
    }

    @Test
    fun `deve deletar saudação`() {
        val created = greetingService.createGreeting("C", "Tchau")
        greetingService.deleteGreeting(created.id!!)
        assertNull(greetingService.getGreeting(created.id))
    }
}

// Implementação fake para teste
class InMemoryGreetingPort : GreetingPort {
    private val data = mutableMapOf<Long, Greeting>()
    private var idSeq = 1L
    override fun save(greeting: Greeting): Greeting {
        val id = greeting.id ?: idSeq++
        val saved = Greeting(id, greeting.name, greeting.message)
        data[id] = saved
        return saved
    }
    override fun findById(id: Long) = data[id]
    override fun findAll() = data.values.toList()
    override fun deleteById(id: Long) { data.remove(id) }
}

