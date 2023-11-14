package com.skripsi.data.repositories.data_master

import com.skripsi.data.data_source.DatabaseFactory
import com.skripsi.data.entities.*
import com.skripsi.data.entities.ItemBarangTable.kode
import com.skripsi.domain.models.master.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.jetbrains.exposed.sql.innerJoin
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class DataMasterRepositoryImpl(
    private val db: DatabaseFactory
) : DataMasterRepository {

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

    override suspend fun getKategori(): List<Kategori> {
        GolonganTable.apply {
            return transaction(db.dbInstance) {
                selectAll().map { row ->
                    Kategori(
                        name = row[kategori]
                    )
                }
            }
        }
    }

    override suspend fun getGolongan(): List<Golongan> {
        GolonganTable.apply {
            return transaction(db.dbInstance) {
                selectAll().map { row ->
                    Golongan(
                        name = row[kode]
                    )
                }
            }
        }
    }

    override suspend fun getBarang(): List<Barang> {
        ItemBarangTable.apply {
            return transaction(db.dbInstance) {
                selectAll().map { row ->
                    Barang(
                        kodeBarang = row[kode],
                        name = row[nama]
                    )
                }
            }
        }
    }


    private fun getRawPenjualan(): List<Penjualan> {
        ItemPenjualanTable.apply {
            return transaction(db.dbInstance) {
                selectAll().map { row ->
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
            return transaction(db.dbInstance) {
                selectAll().limit(n = 10000).map { row ->
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

    override suspend fun getDataMentah(): List<DataTransaksi> {
        return transaction(db.dbInstance) {
            ItemBarangTable
                .innerJoin(StokTable, { kode }, { kode })
                .innerJoin(ItemPenjualanTable, { kode }, { kodeBarang })
                .innerJoin(ItemPembelianTable, { kode }, { kodeBarang })
                .selectAll()
                .map { resultRow ->
                    DataTransaksi(
                        kodeBarang = resultRow[kode],
                        namaBarang = resultRow[ItemBarangTable.nama],
                        stok = resultRow[StokTable.stok],
                        penjualan = resultRow[ItemPenjualanTable.qty],
                        pembelian = resultRow[ItemPembelianTable.qty]
                    )
                }
        }

    }

    override suspend fun getDataRunBlocking(): List<DataTransaksi> = coroutineScope {
        return@coroutineScope async {
            transaction(db.dbInstance) {
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
                            isDiskon = resultRow[IsDiskonTable.isDiskon]
                        )
                    }
            }
        }.await()
    }
}