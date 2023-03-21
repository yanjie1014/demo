package com.example.demo.view.attractions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.databinding.AttractionsItemBinding
import com.example.demo.network.response.AttractionsItem

class AttractionsAdapter(
    private val attractions : List<AttractionsItem>
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
        val attractionsItem = attractions[position]
        if (attractionsItem.images.isEmpty()) {
            attractionsItem.images += AttractionsItem.Image("", "")
        }
        holder.attractionsItemBinding.attractionsItem = attractionsItem
    }

    inner class AttractionsViewHolder(
        val attractionsItemBinding: AttractionsItemBinding
    ) : RecyclerView.ViewHolder(attractionsItemBinding.root)

}