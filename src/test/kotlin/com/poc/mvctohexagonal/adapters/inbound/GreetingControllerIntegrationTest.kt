package com.poc.mvctohexagonal.adapters.inbound

import com.fasterxml.jackson.databind.ObjectMapper
import com.poc.mvctohexagonal.domain.model.Greeting
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class GreetingControllerIntegrationTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @Test
    fun `deve criar, buscar, listar e deletar saudação via REST`() {
        // Criar
        val request = GreetingRequest("Maria", "Bem-vinda!")
        val createResult = mockMvc.perform(post("/greetings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk)
            .andReturn()
        val created = objectMapper.readValue(createResult.response.contentAsString, Greeting::class.java)
        assertEquals("Maria", created.name)
        assertEquals("Bem-vinda!", created.message)
        assertNotNull(created.id)

        // Buscar
        mockMvc.perform(get("/greetings/${created.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Maria"))

        // Listar
        mockMvc.perform(get("/greetings"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].name").value("Maria"))

        // Deletar
        mockMvc.perform(delete("/greetings/${created.id}"))
            .andExpect(status().isNoContent)
        mockMvc.perform(get("/greetings/${created.id}"))
            .andExpect(status().isNotFound)
    }
}

