package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.databinding.ItemHabitBinding
import com.example.habittracker.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>) :
    RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {

    class HabitViewHolder(val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun getItemCount(): Int = habitList.size

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]

        holder.binding.txtName.text = habit.name
        holder.binding.txtDesc.text = habit.description

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        holder.binding.txtProgressValue.text =
            "${habit.progress} / ${habit.goal} glasses"

        val context = holder.itemView.context

        if (habit.progress >= habit.goal) {

            // STATUS
            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setBackgroundColor(
                context.getColor(R.color.green)
            )
            holder.binding.txtStatus.setTextColor(
                context.getColor(android.R.color.white)
            )

            // CHECK ICON
            holder.binding.imgCheck.visibility = android.view.View.VISIBLE

            // PROGRESS BAR HIJAU
            holder.binding.progressBar.progressTintList =
                android.content.res.ColorStateList.valueOf(
                    context.getColor(R.color.green)
                )

            // GARIS KIRI
            holder.binding.leftIndicator.setBackgroundColor(
                context.getColor(R.color.green)
            )

            // DISABLE BUTTON
            holder.binding.btnPlus.isEnabled = false
            holder.binding.btnMinus.isEnabled = false

        } else {

            holder.binding.txtStatus.text = "In Progress"
            holder.binding.txtStatus.setBackgroundColor(
                android.graphics.Color.GRAY
            )
            holder.binding.txtStatus.setTextColor(
                context.getColor(android.R.color.white)
            )

            holder.binding.imgCheck.visibility = android.view.View.GONE

            holder.binding.progressBar.progressTintList =
                android.content.res.ColorStateList.valueOf(
                    context.getColor(android.R.color.holo_purple)
                )

            holder.binding.leftIndicator.setBackgroundColor(
                android.graphics.Color.TRANSPARENT
            )

            // ENABLE BUTTON
            holder.binding.btnPlus.isEnabled = true
            holder.binding.btnMinus.isEnabled = true
        }

        // BUTTON PLUS
        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress++
                notifyItemChanged(position)
            }
        }

        // BUTTON MINUS
        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--
                notifyItemChanged(position)
            }
        }
    }

    fun updateData(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
}