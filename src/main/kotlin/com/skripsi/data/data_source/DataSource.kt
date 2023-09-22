package com.skripsi.data.data_source

import com.skripsi.data.entities.DataTraining

object DataSource {
    fun dataTrainingSample(): List<DataTraining> {
        val items = mutableListOf<DataTraining>()
        items.add(
            DataTraining(
                kategori = "Makanan",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Minuman",
                persediaan = "Banyak",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Kebutuhan",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Kebutuhan",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Minuman",
                persediaan = "Banyak",
                isPromosi = true,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Kosmetik",
                persediaan = "Sedikit",
                isPromosi = true,
                isMemenuhiTarget = false
            )
        )
        items.add(
            DataTraining(
                kategori = "Makanan",
                persediaan = "Sedikit",
                isPromosi = false,
                isMemenuhiTarget = true
            )
        )
        items.add(
            DataTraining(
                kategori = "Makanan",
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