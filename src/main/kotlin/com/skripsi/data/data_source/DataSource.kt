package com.skripsi.data.data_source

import com.skripsi.data.entities.DataTraining

object DataSource {
    fun dataTrainingSample(): List<DataTraining> {
        val items = mutableListOf<DataTraining>()
        items.add(
            DataTraining(
                kategori = "Makanan",
                harga = "Rendah",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Minuman",
                harga = "Standar",
                persediaan = "Banyak",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Kebutuhan",
                harga = "Tinggi",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                harga = "Rendah",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Kebutuhan",
                harga = "Tinggi",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                harga = "Standar",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Minuman",
                harga = "Rendah",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                harga = "Tinggi",
                persediaan = "Sedikit",
                isPromosi = true,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Makanan",
                harga = "Standar",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Makanan",
                harga = "Standar",
                persediaan = "Banyak",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        return items.mapIndexed { index, item ->
            item.copy(nama = "${item.nama} ${index+1}")
        }
    }
}