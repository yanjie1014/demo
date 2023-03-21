package com.example.demo.view.attractions

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.databinding.AttractionsItemBinding
import com.example.demo.network.response.AttractionsItem
import com.example.demo.view.attractions.listener.AttractionsItemClickListener

class AttractionsAdapter(
    private val attractions : List<AttractionsItem>,
    private val listener : AttractionsItemClickListener
) : RecyclerView.Adapter<AttractionsAdapter.AttractionsViewHolder>() {

    override fun getItemCount() = attractions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AttractionsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.attractions_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: AttractionsViewHolder, position: Int) {
        holder.attractionsItemBinding.attractionsItem = attractions[position]
        holder.attractionsItemBinding.root.setOnClickListener {
            listener.onClick(attractions[position])
        }
    }

    inner class AttractionsViewHolder(
        val attractionsItemBinding: AttractionsItemBinding
    ) : RecyclerView.ViewHolder(attractionsItemBinding.root)

}