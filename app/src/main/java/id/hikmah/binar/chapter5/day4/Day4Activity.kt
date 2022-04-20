package id.hikmah.binar.chapter5.day4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import id.hikmah.binar.chapter5.databinding.ActivityDay4Binding
import id.hikmah.binar.chapter5.helper.StudentRepo
import id.hikmah.binar.chapter5.helper.viewModelsFactory
import id.hikmah.binar.chapter5.service.APIClient
import id.hikmah.binar.chapter5.service.APIService

class Day4Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDay4Binding
    private val studentRepo : StudentRepo by lazy { StudentRepo( this)}

    private val APIService : APIService by lazy { APIClient.instance }

    //Cara 1 view model tanpa parameter
//    private val viewModel: Day4ViewModel by viewModels()

    //Cara 2b view model tanpa parameter
//    private lateinit var vModel: Day4ViewModel

    //View model dengan parameter
    private val viewModel: Day4ViewModel by viewModelsFactory { Day4ViewModel(studentRepo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDay4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Cara 2 view model tanpa parameter
//        vModel = ViewModelProvider(this)[Day4ViewModel::class.java]

//        withoutViewModel()
        withViewModel()
        viewModel.getData()
        observeData()
    }

    // Tanpa View model

    private fun withoutViewModel(){
        buttonPlusClicked()
        buttonMinusClicked()
    }

    private fun buttonPlusClicked(){
        binding.btnPlus.setOnClickListener{
            val newText = binding.tvCount.text.toString().toInt().plus(1)
            updateText(newText)
        }
    }

    private fun buttonMinusClicked(){
        binding.btnMinus.setOnClickListener{
            val newText = binding.tvCount.text.toString().toInt().minus(1)
            updateText(newText)
        }
    }

    private fun updateText(newValue: Int){
        binding.tvCount.text = newValue.toString()
    }

    // Dengan ViewModel
    private fun withViewModel() {
        binding.btnPlus.setOnClickListener{
            viewModel.increment()
        }

        binding.btnMinus.setOnClickListener{
            viewModel.decrement()
        }

        viewModel.nilaiBaru.observe(this){
            updateText(it)
        }
    }

    private fun observeData(){
        viewModel.students.observe(this) {
            Toast.makeText(this,"${it.size}", Toast.LENGTH_SHORT).show()
        }
    }
}