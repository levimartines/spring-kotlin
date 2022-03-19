package com.levimartines.springkotlin.model

import javax.persistence.*

@Entity
data class Bank(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int? = null,

    @Column(name = "account_number", nullable = false)
    val accountNumber: String? = null,

    @Column(name = "trust", nullable = false)
    var trust: Double? = null,

    @Column(name = "transaction_fee", nullable = false)
    var transactionFee: Int? = null
) {

}