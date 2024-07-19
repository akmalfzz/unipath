package com.example.unipath.jurusan

import com.example.unipath.utils.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class JurusanRepository {
    private val supabase = SupabaseClient.client

    suspend fun getData(): List<Jurusan> {
        return  supabase
            .from("jurusan")
            .select(columns = Columns.ALL)
            .decodeList<Jurusan>()
    }
}