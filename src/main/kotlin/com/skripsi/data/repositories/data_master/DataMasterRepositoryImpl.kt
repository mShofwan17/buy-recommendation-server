package com.skripsi.data.repositories.data_master

import com.skripsi.data.data_source.DatabaseFactory
import com.skripsi.data.entities.GolonganTable
import com.skripsi.data.entities.ItemPenjualanTable
import com.skripsi.domain.models.master.Golongan
import com.skripsi.domain.models.master.Kategori
import com.skripsi.domain.models.master.Penjualan
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

    override suspend fun getKategori(): List<Kategori> {
        GolonganTable.apply {
            return transaction(db.dbInstance){
                selectAll().map { row->
                    Kategori(
                        name = row[kategori]
                    )
                }
            }
        }
    }

    override suspend fun getGolongan(): List<Golongan> {
        GolonganTable.apply {
            return transaction(db.dbInstance){
                selectAll().map { row->
                    Golongan(
                        name = row[kode]
                    )
                }
            }
        }
    }


    private fun getRawPenjualan(): List<Penjualan>{
        ItemPenjualanTable.apply {
            return transaction(db.dbInstance) {
                selectAll().limit(n = 6000).map { row->
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
}