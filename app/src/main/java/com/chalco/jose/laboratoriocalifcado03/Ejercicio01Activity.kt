package com.chalco.jose.laboratoriocalifcado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chalco.jose.laboratoriocalifcado03.databinding.ActivityEjercicio01Binding

class Ejercicio01Activity : AppCompatActivity() {

    private var teacherList: List<TeacherResponse> = emptyList()

    private val adapter by lazy { TeacherAdapter(teacherList) }

    private lateinit var binding: ActivityEjercicio01Binding

    private val viewModel by lazy { Ejercicio01ViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTeacherList.layoutManager = LinearLayoutManager(this)
        binding.rvTeacherList.adapter = adapter
        observeValues()
    }

    private fun observeValues() {
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.teacherList.observe(this) { teachers ->
            adapter.list = teachers
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}