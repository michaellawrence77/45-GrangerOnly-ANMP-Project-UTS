package com.example.habittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.R
import com.example.habittracker.databinding.ItemHabitBinding
import com.example.habittracker.model.Habit

class HabitListAdapter(
    private val habitList: ArrayList<Habit>,
    private val onHabitUpdated: (Habit) -> Unit
) :
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
        holder.binding.imgIcon.setImageResource(habit.icon)

        holder.binding.progressBar.max = habit.goal
        holder.binding.progressBar.progress = habit.progress

        holder.binding.txtProgressValue.text =
            "${habit.progress} / ${habit.goal} ${habit.unit}"

        val context = holder.itemView.context

        if (habit.progress >= habit.goal) {

            holder.binding.txtStatus.text = "Completed"
            holder.binding.txtStatus.setBackgroundColor(
                context.getColor(R.color.green)
            )
            holder.binding.txtStatus.setTextColor(
                context.getColor(android.R.color.white)
            )

            holder.binding.imgCheck.visibility = android.view.View.VISIBLE

            holder.binding.progressBar.progressTintList =
                android.content.res.ColorStateList.valueOf(
                    context.getColor(R.color.green)
                )

            holder.binding.leftIndicator.setBackgroundColor(
                context.getColor(R.color.green)
            )

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

            holder.binding.btnPlus.isEnabled = true
            holder.binding.btnMinus.isEnabled = true
        }

        holder.binding.btnPlus.setOnClickListener {
            if (habit.progress < habit.goal) {
                habit.progress++
                notifyItemChanged(position)
                onHabitUpdated(habit)
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.progress > 0) {
                habit.progress--
                notifyItemChanged(position)
                onHabitUpdated(habit)
            }
        }
    }

    fun updateData(newList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newList)
        notifyDataSetChanged()
    }
}