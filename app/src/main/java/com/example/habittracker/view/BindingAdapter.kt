package com.example.habittracker.view

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("habitIcon")
fun setHabitIcon(view: ImageView, icon: Int) {
    view.setImageResource(icon)
}

@BindingAdapter("habitProgress", "habitGoal", requireAll = true)
fun setProgress(
    progressBar: ProgressBar,
    progress: Int,
    goal: Int
) {
    progressBar.max = goal
    progressBar.progress = progress
}

@BindingAdapter(
    value = ["habitProgressText", "habitGoalText", "habitUnitText"],
    requireAll = true
)
fun setProgressText(
    textView: TextView,
    progress: Int,
    goal: Int,
    unit: String
) {
    textView.text = "$progress / $goal $unit"
}