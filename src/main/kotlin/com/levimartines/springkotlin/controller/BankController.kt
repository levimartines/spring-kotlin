package com.levimartines.springkotlin.controller

import com.levimartines.springkotlin.model.Bank
import com.levimartines.springkotlin.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> = bankService.getBanks()

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = bankService.getBankByAccountNumber(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postBank(@RequestBody bank: Bank) = bankService.save(bank)

    @PutMapping("/{accountNumber}")
    fun putBank(@PathVariable accountNumber: String, @RequestBody bank: Bank): Bank = bankService.update(accountNumber, bank)
}