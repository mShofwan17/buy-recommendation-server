package com.skripsi.data.data_source

import org.jetbrains.exposed.sql.Database

class DatabaseFactory {
    private var _database : Database ?= null
    val dbInstance get() = _database
    init {
        val url = "jdbc:mysql://localhost:3306/db_penjualan"
        val driverName = "com.mysql.cj.jdbc.Driver"
        _database = Database.connect(
            url,
            driverName,
            user = "root"
        )
    }

  /*  suspend fun <T> dbQuery(block: suspend () -> T):T {
        return newSuspendedTransaction(Dispatchers.IO) { block() }
    }*/
}