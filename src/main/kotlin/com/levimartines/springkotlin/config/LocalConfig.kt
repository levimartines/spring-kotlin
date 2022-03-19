package com.levimartines.springkotlin.config

import com.levimartines.springkotlin.datasource.BankRepository
import com.levimartines.springkotlin.model.Bank
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("local")
class LocalConfig(val bankRepository: BankRepository) {

    @Bean
    fun instantiateBanks(): Boolean {
        bankRepository.saveAll(listOf(
            Bank(null, "101", 17.0, 4),
            Bank(null, "102", 44.0, 2),
            Bank(null, "103", 32.0, 6),
            Bank(null, "104", 82.0, 2),
        ))
        return true
    }


}