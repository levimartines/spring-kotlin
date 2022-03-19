package com.levimartines.springkotlin.datasource

import com.levimartines.springkotlin.model.Bank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankRepository : JpaRepository<Bank, Int?> {

    fun getByAccountNumber(accountNumber: String): Optional<Bank>
}