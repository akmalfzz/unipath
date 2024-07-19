package com.example.unipath.diagnose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Answer(
    val minatCode: String,
    val cf:Double
)

@Serializable
data class Result(
    val jurusan: String,
    val cf: Double
)
@Serializable
data class Minat(
    @SerialName("minat_code")
    val minatCode: String,
    @SerialName("minat_name")
    val minatName: String
)

@Serializable
data class MinatJurusan(
    @SerialName("minat_code")
    val minatCode: String,
    @SerialName("jurusan_code")
    val jurusanCode: String,
    @SerialName("certainty_factor")
    val nilaiCf: Double,
)

