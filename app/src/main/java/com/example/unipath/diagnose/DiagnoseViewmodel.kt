package com.example.unipath.diagnose

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unipath.history.DiagnosesHistory
import com.example.unipath.jurusan.Jurusan
import com.example.unipath.jurusan.JurusanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiagnoseViewmodel : ViewModel() {
    private val repository = MinatRepository()
    private val repositoryJurusan = JurusanRepository()
    private val _quetion = MutableStateFlow<List<Minat>>(emptyList())
    val quetion: StateFlow<List<Minat>> = _quetion
    private  val _isFinish =  MutableStateFlow(false)
    val isFinish : StateFlow<Boolean> = _isFinish

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex

    private val _minatJurusan = MutableStateFlow<List<MinatJurusan>>(emptyList())
    private val _jurusan = MutableStateFlow<List<Jurusan>>(emptyList())

    private val _results = MutableStateFlow<List<Result>>(emptyList())
    val results: StateFlow<List<Result>> = _results
    init {
        fetchQuestion()
        fetchJurusan()
        fetchMinatJurusan()
    }

    private val _answer = MutableStateFlow<List<Answer>>(emptyList())


    private fun fetchQuestion() {
        viewModelScope.launch {
            val result = repository.getData()
            _quetion.value = result
        }
    }

    private fun fetchMinatJurusan() {
        viewModelScope.launch {
            val result = repository.getMinatJurusan()
            _minatJurusan.value = result
        }
    }

    private fun fetchJurusan() {
        viewModelScope.launch {
            val result = repositoryJurusan.getData()
            _jurusan.value = result
        }
    }
    fun answerQuestion(questionCode: String, cf: Double) {
        _answer.value += Answer(questionCode, cf)
        if (_currentQuestionIndex.value < _quetion.value.size - 1) {
            _currentQuestionIndex.value++
        }else{
            _currentQuestionIndex.value++
            calculateCF()
            _isFinish.value = true
        }
    }

    private fun calculateCF() {
        val answers = _answer.value
        val jurusanCFMap = mutableMapOf<String, MutableList<Double>>()

        for (answer in answers) {
            val minatJurusan = _minatJurusan.value.filter { it.minatCode == answer.minatCode }
            for (gp in minatJurusan) {
                val cfValue = gp.nilaiCf * answer.cf
                if (jurusanCFMap.containsKey(gp.jurusanCode)) {
                    jurusanCFMap[gp.jurusanCode]?.add(cfValue)
                } else {
                    jurusanCFMap[gp.jurusanCode] = mutableListOf(cfValue)
                }
            }
        }

        val results = jurusanCFMap.map { (jurusanCode, cfList) ->
            val jurusanName = _jurusan.value.find { it.jurusanCode == jurusanCode }?.jurusan ?: "Unknown"
            val combinedCF = combineCFSequentially(cfList)
            Result(jurusanName, combinedCF * 100)
        }.sortedByDescending { it.cf }

        _results.value = results
    }

    private fun combineCFSequentially(cfList: List<Double>): Double {
        if (cfList.isEmpty()) return 0.0
        var combinedCF = cfList[0]
        for (i in 1 until cfList.size) {
            combinedCF = combineCF(combinedCF, cfList[i])
        }
        return combinedCF
    }

    private fun combineCF(cf1: Double, cf2: Double): Double {
        return cf1 + cf2 * (1 - cf1)
    }

    fun clearResults() {
        _answer.value = emptyList()
        _results.value = emptyList()
        _currentQuestionIndex.value = 0
    }

    @SuppressLint("MutatingSharedPrefs")
    fun saveDiagnoseResult(context: Context, userName: String) {
        val highestResult = _results.value.maxByOrNull {it.cf}
        val highestCf = highestResult?.cf ?:0.0
        val diagnoseResult = if (highestCf == 0.0){
            "Tidak Cocok"
        }else{
            highestResult?.jurusan ?: "Unknown"
        }
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Save to SharedPreferences
        val sharedPreferences = context.getSharedPreferences("DiagnoseHistory", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val newEntry = "$userName|$diagnoseResult|$date"
        val existingEntries = sharedPreferences.getStringSet("history", mutableSetOf()) ?: mutableSetOf()
        val newEntries = existingEntries.toMutableSet()
        newEntries.add(newEntry)
        editor.putStringSet("history", newEntries)
        editor.apply()
    }

    fun getDiagnoseHistory(context: Context): List<DiagnosesHistory> {
        val sharedPreferences = context.getSharedPreferences("DiagnoseHistory", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("history", mutableSetOf()) ?: mutableSetOf()
        return historySet.map { entry ->
            val parts = entry.split("|")
            DiagnosesHistory(parts[0], parts[1], parts[2])
        }
    }
}
