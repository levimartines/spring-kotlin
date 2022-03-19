package com.levimartines.springkotlin.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.levimartines.springkotlin.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    val bankUrl = "/api/banks";

    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            mockMvc.get(bankUrl).andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("101") }
                }
        }

    }

    @Nested
    @DisplayName("getBankByAccountNumber()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBankByAccountNumber {
        @Test
        fun `should return the bank with the given account number`() {
            val accountNumber = "101"
            mockMvc.get("$bankUrl/$accountNumber").andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value(accountNumber) }
                }
        }

        @Test
        fun `should return 404 when dont find the bank with the given account number`() {
            val accountNumber = "30982130921"
            mockMvc.get("$bankUrl/$accountNumber").andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("postBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBank {

        @Test
        fun `should return 201 when created`() {
            val accountNumber = "105"
            val bank = Bank(null, accountNumber, 45.0, 2)
            mockMvc.post(bankUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.accountNumber") { value(accountNumber) }
                    }
                }
        }

        @Test
        fun `should return 400 when not unique account number`() {
            val accountNumber = "101"
            val invalidBank = Bank(null, accountNumber, 45.0, 2)
            mockMvc.post(bankUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }.andDo { print() }
                .andExpect { status { isBadRequest() } }
        }
    }

    @Nested
    @DisplayName("putBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PutBank {

        @Test
        fun `should return 200 with the updated content`() {
            val accountNumber = "101"
            val bank = Bank(null, accountNumber, 100.0, 0)
            mockMvc.put("$bankUrl/$accountNumber") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.trust") { value(100.0) }
                    }
                }
        }

        @Test
        fun `should return 404 when dont find the bank with the given account number`() {
            val accountNumber = "999"
            val bank = Bank(null, null, 100.0, 0)
            mockMvc.put("$bankUrl/$accountNumber") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }.andDo { print() }
                .andExpect { status { isNotFound() } }
        }

    }
}