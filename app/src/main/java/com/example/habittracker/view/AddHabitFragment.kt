package com.example.habittracker.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class AddHabitFragment : Fragment(R.layout.fragment_add_habit) {

    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAddHabitBinding.bind(view)

        val iconList = arrayOf("Fitness", "Study", "Drink", "Meditation")
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, iconList)
        binding.spinnerIcon.adapter = adapter

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCreate.setOnClickListener {

            val name = binding.etName.text.toString()
            val desc = binding.etDesc.text.toString()
            val goalStr = binding.etGoal.text.toString()
            val unit = binding.etUnit.text.toString()

            if (name.isEmpty() || desc.isEmpty() || goalStr.isEmpty() || unit.isEmpty()) {
                Toast.makeText(requireContext(), "All fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val goal = goalStr.toInt()

            val iconRes = when (binding.spinnerIcon.selectedItem.toString()) {
                "Fitness" -> R.drawable.ic_fitness
                "Study" -> R.drawable.ic_study
                "Drink" -> R.drawable.ic_drink
                "Meditation" -> R.drawable.ic_meditation
                else -> android.R.drawable.ic_menu_help
            }

            val habit = Habit(name, desc, goal, 0, iconRes, unit)

            viewModel.addHabit(requireContext(), habit)

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}