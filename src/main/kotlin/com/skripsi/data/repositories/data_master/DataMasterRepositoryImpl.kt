package com.skripsi.data.repositories.data_master

import com.skripsi.data.data_source.DatabaseFactory
import com.skripsi.data.entities.*
import com.skripsi.data.entities.ItemBarangTable.kode
import com.skripsi.domain.models.master.*
import com.skripsi.utils.asPembelian
import com.skripsi.utils.asPenjualan
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.innerJoin
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DataMasterRepositoryImpl(
    private val db: DatabaseFactory
) : DataMasterRepository {

    override val dbInstance: Database?
        get() = db.dbInstance
    override suspend fun getPenjualan(): List<Penjualan> {
        return getRawPenjualan().groupBy { it.kodeBarang }
            .map { (kodeBarang, items) ->
                val namaBarang = items.first().namaBarang
                val qty = items.sumOf { it.qty }
                val harga = items.sumOf { it.harga }
                Penjualan(
                    id = 0,
                    kodeBarang = kodeBarang,
                    namaBarang = namaBarang,
                    qty = qty,
                    harga = harga
                )
            }
    }

    override suspend fun getPembelian(): List<Pembelian> {
        return getRawPembelian().groupBy { it.kodeBarang }
            .map { (kodeBarang, items) ->
                val namaBarang = items.first().namaBarang
                val qty = items.sumOf { it.qty }
                val harga = items.sumOf { it.harga }
                Pembelian(
                    id = 0,
                    kodeBarang = kodeBarang,
                    namaBarang = namaBarang,
                    qty = qty,
                    harga = harga
                )
            }
    }

    override suspend fun getBarang(): List<Barang> {
        ItemBarangTable.apply {
            return transaction(dbInstance) {
                selectAll().map { row ->
                    Barang(
                        kodeBarang = row[kode],
                        name = row[nama],
                        kategori = row[kategori]
                    )
                }
            }
        }
    }

    override suspend fun getStok(): List<Stok> {
        StokTable.apply {
            return transaction(dbInstance) {
                selectAll().map { row ->
                    Stok(
                        kode = row[kode],
                        stok = row[stok]
                    )
                }
            }
        }
    }

    override suspend fun getBarangDiskon(): List<BarangDiskon> {
        IsDiskonTable.apply {
            return transaction(db.dbInstance) {
                selectAll().map { row ->
                    BarangDiskon(
                        kode = row[kode],
                        isDiskon = row[isDiskon]
                    )
                }
            }
        }
    }


    private fun getRawPenjualan(): List<Penjualan> {
        ItemPenjualanTable.apply {
            return transaction(dbInstance) {
                selectAll().asPenjualan { row ->
                    Penjualan(
                        id = 0,
                        kodeBarang = row[kodeBarang],
                        namaBarang = row[namaBarang],
                        qty = row[qty],
                        harga = row[harga],
                    )
                }
            }
        }
    }

    private fun getRawPembelian(): List<Pembelian> {
        ItemPembelianTable.apply {
            return transaction(dbInstance) {
                selectAll().asPembelian { row ->
                    Pembelian(
                        id = 0,
                        kodeBarang = row[kodeBarang],
                        namaBarang = row[namaBarang],
                        qty = row[qty],
                        harga = row[harga],
                    )
                }
            }
        }
    }

    override suspend fun getDataTransaksi(): List<DataTransaksi> {
        return transaction(dbInstance) {
            ItemBarangTable
                .innerJoin(StokTable, { kode }, { kode })
                .innerJoin(IsDiskonTable, { kode }, { kode })
                .selectAll()
                .map { resultRow ->
                    DataTransaksi(
                        kodeBarang = resultRow[kode],
                        namaBarang = resultRow[ItemBarangTable.nama],
                        golongan = resultRow[ItemBarangTable.kategori],
                        stok = resultRow[StokTable.stok],
                        isDiskon = 0//resultRow[IsDiskonTable.isDiskon]
                    )
                }
        }
    }
}