package com.levimartines.springkotlin.service

import com.levimartines.springkotlin.datasource.BankRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest {

    private val dataSource: BankRepository = mockk(relaxed = true)
    private val bankService = BankService(dataSource)

    @Test
    fun `should retrieve banks`() {
        bankService.getBanks()
        verify(exactly = 1) { dataSource.findAll() }
    }

}