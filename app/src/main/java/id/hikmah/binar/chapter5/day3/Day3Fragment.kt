package id.hikmah.binar.chapter5.day3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import id.hikmah.binar.chapter5.R
import id.hikmah.binar.chapter5.adapter.CarAdapter
import id.hikmah.binar.chapter5.databinding.FragmentDay3Binding
import id.hikmah.binar.chapter5.databinding.LayoutDialogAddBinding
import id.hikmah.binar.chapter5.model.CarItem
import id.hikmah.binar.chapter5.service.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Day3Fragment : Fragment() {

    private var _binding: FragmentDay3Binding? = null
    private val binding get() = _binding!!

    private lateinit var carAdapter : CarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDay3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        getDataFromNetwork()
        onAddClicked()
    }

    private fun initRecyclerView(){
        carAdapter = CarAdapter()
        binding.rvCar.apply {
            adapter = carAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getDataFromNetwork(){
        val apiService = APIClient.instance
        apiService.getAllCar().enqueue(object : Callback<List<CarItem>> {
            override fun onResponse(call: Call<List<CarItem>>, response: Response<List<CarItem>>) {
                //isSuccessful = code() == 200
                if (response.isSuccessful){
                    if (!response.body().isNullOrEmpty()){
                        response.body()?.let {
                            carAdapter.updateData(it)
                        }
                    }
                }
                binding.pbCar.isVisible = false
            }

            override fun onFailure(call: Call<List<CarItem>>, t: Throwable){
                binding.pbCar.isVisible = false
            }
        })
    }

    private fun onAddClicked(){
        binding.fabAdd.setOnClickListener{
//            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
            createCustomDialog{email, password ->
                Toast.makeText(requireContext(), "email: $email, password: $password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createCustomDialog(onClickListener: (email: String, password: String) -> Unit) {
        val binding = LayoutDialogAddBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        val customLayout = binding.root

        val builder = AlertDialog.Builder(requireContext())

        builder.setView(customLayout)

        val dialog = builder.create()

        binding.apply {
            btnSave.setOnClickListener {
                onClickListener.invoke(etEmail.text.toString(), etPassword.text.toString())
                dialog.dismiss()
            }
        }

        dialog.show()
    }

}