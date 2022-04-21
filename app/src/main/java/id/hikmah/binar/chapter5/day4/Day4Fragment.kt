package id.hikmah.binar.chapter5.day4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import id.hikmah.binar.chapter5.R
import id.hikmah.binar.chapter5.databinding.FragmentDay4Binding
import id.hikmah.binar.chapter5.helper.StudentRepo
import id.hikmah.binar.chapter5.helper.viewModelsFactory
import id.hikmah.binar.chapter5.service.APIClient
import id.hikmah.binar.chapter5.service.APIService

class Day4Fragment : Fragment() {

    private var _binding: FragmentDay4Binding? = null
    private val binding get() = _binding!!

    private val studentRepo: StudentRepo by lazy { StudentRepo(requireContext())}

    private val apiService : APIService by lazy{ APIClient.instance}

    private val viewModel: Day4ViewModel by viewModelsFactory { Day4ViewModel(studentRepo)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDay4Binding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        withViewModel()
        viewModel.addData()
        observeData()
    }

    // Dengan ViewModel
    private fun withViewModel() {
        binding.btnPlus.setOnClickListener{
            viewModel.increment()
        }

        binding.btnMinus.setOnClickListener{
            viewModel.decrement()
        }

        viewModel.nilaiBaru.observe(requireActivity()){
            updateText(it)
        }
    }

    private fun updateText(newValue: Int){
        binding.tvCount.text = newValue.toString()
    }

    private fun observeData(){
        viewModel.addData.observe(requireActivity()){
            viewModel.getData()
        }
        viewModel.students.observe(requireActivity()) {
            Toast.makeText(requireContext(),"${it.size}", Toast.LENGTH_SHORT).show()
        }
    }

}