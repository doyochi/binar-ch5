package id.hikmah.binar.chapter5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.hikmah.binar.chapter5.R
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(listDiffer.currentList[position])
    }

    override fun getItemCount(): Int = listDiffer.currentList.size

    inner class CarViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val ivCar = view.findViewById<ImageView>(R.id.iv_car)
        private val tvCarName = view.findViewById<TextView>(R.id.tv_car_name)

        fun bind(item: CarItem){
            tvCarName.text = item.name
        }
    }
}
