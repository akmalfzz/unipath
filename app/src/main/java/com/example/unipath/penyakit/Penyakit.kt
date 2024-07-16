package com.example.unipath.penyakit

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Penyakit(
    @SerialName("jurusan_id")
    val penyakitId: Int,
    @SerialName("jurusan_code")
    val penyakitCode: String,
    @SerialName("jurusan_name")
    val penyakit: String,
    @SerialName("deskripsi")
    val deskripsi: String
)
