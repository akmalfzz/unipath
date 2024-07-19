package com.example.unipath.diagnose

import com.example.unipath.utils.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class MinatRepository {
    private val supabase = SupabaseClient.client

    suspend fun getData(): List<Minat> {
        return  supabase
            .from("minat")
            .select(Columns.ALL)
            .decodeList<Minat>()
    }

    suspend fun getMinatJurusan(): List<MinatJurusan> {
        return supabase
            .from("jurusan_minat")
            .select(Columns.ALL)
            .decodeList<MinatJurusan>()
    }
}