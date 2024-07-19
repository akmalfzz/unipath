package com.example.unipath.jurusan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JurusanViewModel : ViewModel() {
    private val repository = JurusanRepository()
    private val _data = MutableStateFlow<List<Jurusan>>(emptyList())
    val data: StateFlow<List<Jurusan>> get() = _data
    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val result = repository.getData()
            _data.value = result
        }
    }

}