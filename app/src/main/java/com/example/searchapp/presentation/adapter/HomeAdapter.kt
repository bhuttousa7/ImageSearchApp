package com.example.searchapp.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchapp.data.model.ImageResponse
import com.example.searchapp.databinding.SingleItemBinding
import com.example.searchapp.presentation.di.ImageResponseHolder

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ImageResponse>() {
        override fun areItemsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)

    private var onItemClickListener : ((ImageResponse)-> Unit) = {
        Log.i("HomeAdapter", Unit.toString())
    }

    fun setOnItemClickListener(listener : (ImageResponse)-> Unit){
        onItemClickListener = listener
    }

    inner class HomeViewHolder(private val binding : SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(imageResponse: ImageResponse){

            Glide.with(binding.itemImage)
                .load(imageResponse.previewURL)
                .override(imageResponse.previewWidth,imageResponse.previewHeight)
                .fitCenter()
                .into(binding.itemImage)
            binding.itemTitle.text = imageResponse.user
            binding.itemTags.text = imageResponse.tags

            binding.itemView.setOnClickListener {
                onItemClickListener.invoke(imageResponse)
                ImageResponseHolder.selectedImageResponse = imageResponse

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() =  differ.currentList.size

}