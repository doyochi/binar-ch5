package id.hikmah.binar.chapter5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.hikmah.binar.chapter5.R
import id.hikmah.binar.chapter5.databinding.ItemCarBinding
import id.hikmah.binar.chapter5.model.CarItem

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarViewHolder>(){

    private val diffCallback = object : DiffUtil.ItemCallback<CarItem>(){
        override fun areItemsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CarItem, newItem: CarItem): Boolean {
            TODO("Not yet implemented")
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffCallback)

    fun updateData(cars: List<CarItem>) = listDiffer.submitList(cars)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    /*
    View holder wajib extend RecyclerView ViewHolder
    ViewHolder butuh view, maka kita tambahkan parameter view

    Untuk view binding
    bidning.root == view
    jd kita bisa mengganti view dengan binding.root
     */

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: CarItem){
            binding.apply {
                tvCarName.text = item.name
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(ivCar)
            }
        }
    }
}
