package com.example.unipath.jurusan

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Jurusan(
    @SerialName("jurusan_id")
    val jurusanId: Int,
    @SerialName("jurusan_code")
    val jurusanCode: String,
    @SerialName("jurusan_name")
    val jurusan: String,
    @SerialName("deskripsi")
    val deskripsi: String
)
