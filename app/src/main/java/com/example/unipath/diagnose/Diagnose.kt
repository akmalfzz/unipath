package com.example.unipath.diagnose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Answer(
    val gejalaCode: String,
    val cf:Double
)

@Serializable
data class Result(
    val penyakit: String,
    val cf: Double
)
@Serializable
data class Gejala(
    @SerialName("minat_code")
    val gejalaCode: String,
    @SerialName("minat_name")
    val gejalaName: String
)

@Serializable
data class GejalaPenyakit(
    @SerialName("minat_code")
    val gejalaCode: String,
    @SerialName("jurusan_code")
    val penyakitCode: String,
    @SerialName("certainty_factor")
    val nilaiCf: Double,
)

