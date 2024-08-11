package com.dheril.intern_test.screen.thirdscreen

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dheril.intern_test.data.response.DataItem
import com.dheril.intern_test.databinding.ItemUserBinding
import com.dheril.intern_test.screen.secondscreen.SecondScreenActivity

class ThirdScreenAdapter : PagingDataAdapter<DataItem, ThirdScreenAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener{
                val context = holder.itemView.context
                val moveToHome = Intent(context, SecondScreenActivity::class.java)
                moveToHome.putExtra(SecondScreenActivity.EXTRA_USERNAME, "${data.firstName} ${data.lastName}")
                context.startActivity(moveToHome)
            }
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            binding.tvItemName.text = "${data.firstName} ${data.lastName}"
            binding.tvItemEmail.text = data.email
            Glide.with(itemView.context)
                .load(data.avatar)
                .into(binding.ivItemPhoto)

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}