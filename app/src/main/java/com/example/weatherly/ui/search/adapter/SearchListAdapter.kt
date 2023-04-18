package com.example.weatherly.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherly.Coordinates
import com.example.weatherly.R
import com.example.weatherly.databinding.SearchItemBinding
import com.example.weatherly.rest.model.data_class.CityResponse

lateinit var bindind: SearchItemBinding


class SearchListAdapter(var list : CityResponse, private val itemClickCallback: ((Coordinates, String) -> Unit)) : RecyclerView.Adapter<SearchListAdapter.SearchListVH>() {

    fun onRefresh(listData: CityResponse) {
        this.list = listData
        notifyDataSetChanged()
    }

    inner class SearchListVH(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(cityResponseItem: CityResponse.CityResponseItem) {
            with(binding){
                cityData = cityResponseItem
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListVH {
        bindind = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchListVH(bindind)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchListVH, position: Int) {

        holder.setData(list[position])
        holder.itemView.setOnClickListener {
            itemClickCallback.invoke(Coordinates(list[position].lat, list[position].lon),
                "${ list[position].name }, ${ list[position].country }")
            Navigation.findNavController(it).navigate(R.id.nav_home)
        }
    }


}