package com.example.drwebtest.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.drwebtest.databinding.ItemAppBinding
import com.example.drwebtest.domain.models.InstalledApp

class MainAdapter(private val onClick: (InstalledApp) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    val installedApps = mutableListOf<InstalledApp>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val binding = ItemAppBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
        val item = installedApps[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int = installedApps.size

    fun updateInstalledApps(appsList: List<InstalledApp>) {
        val diffCallback = InstalledAppsDiffCallback(installedApps, appsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        installedApps.clear()
        installedApps.addAll(appsList)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class MainViewHolder(val binding: ItemAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: InstalledApp) {
            binding.itemAppNameText.text = item.appName
            binding.itemAppVersionText.text = "ver. ${item.appVersion}"
            binding.root.setOnClickListener {
                onClick(item)
            }

        }
    }

    class InstalledAppsDiffCallback(
        private val oldList: List<InstalledApp>,
        private val newList: List<InstalledApp>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldList[oldItemPosition].appName == newList[newItemPosition].appName
        }

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}