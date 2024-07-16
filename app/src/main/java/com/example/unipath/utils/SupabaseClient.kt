package com.example.unipath.utils

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    private const val SUPABASE_URL = "https://vovrudghygizsphwhucy.supabase.co"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZvdnJ1ZGdoeWdpenNwaHdodWN5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjA2ODM5OTUsImV4cCI6MjAzNjI1OTk5NX0.XAQd9ZftDEbSeFG2c0D8HcFOUqNYdlN8gL_mALWoL6s"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_KEY
    ) {
        install(Postgrest)
    }
}