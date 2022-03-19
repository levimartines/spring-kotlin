package com.levimartines.springkotlin.service

import com.levimartines.springkotlin.datasource.BankRepository
import com.levimartines.springkotlin.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepository) {
    fun getBanks(): Collection<Bank> = bankRepository.findAll()

    fun getBankByAccountNumber(accountNumber: String): Bank = bankRepository.getByAccountNumber(accountNumber)
        .orElseThrow { NoSuchElementException() }

    fun save(bank: Bank): Bank {
        if (bank.accountNumber == null || bankRepository.getByAccountNumber(bank.accountNumber).isPresent) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }
        return bankRepository.save(bank)
    }

    fun update(accountNumber: String, bank: Bank): Bank {
        val bankToUpdate = getBankByAccountNumber(accountNumber)
        bankToUpdate.transactionFee = bank.transactionFee
        bankToUpdate.trust = bank.trust
        return bankRepository.save(bank)
    }

}