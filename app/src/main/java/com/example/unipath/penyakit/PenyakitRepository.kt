package com.example.unipath.penyakit

import com.example.unipath.utils.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class PenyakitRepository {
    private val supabase = SupabaseClient.client

    suspend fun getData(): List<Penyakit> {
        return  supabase
            .from("jurusan")
            .select(columns = Columns.ALL)
            .decodeList<Penyakit>()
    }
}