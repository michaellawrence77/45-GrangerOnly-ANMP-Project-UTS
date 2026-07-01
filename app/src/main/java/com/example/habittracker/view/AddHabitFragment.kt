package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class AddHabitFragment : Fragment() {

    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_habit,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val iconList = arrayOf("Fitness", "Study", "Drink", "Meditation")
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, iconList)
        binding.spinnerIcon.adapter = adapter

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnCreate.setOnClickListener {
            val name = viewModel.habitName.value ?: ""
            val desc = viewModel.habitDescription.value ?: ""
            val goalText = viewModel.habitGoal.value ?: ""
            val unit = viewModel.habitUnit.value ?: ""

            if (name.isBlank() ||
                desc.isBlank() ||
                goalText.isBlank() ||
                unit.isBlank()) {

                Toast.makeText(
                    requireContext(),
                    "All fields must be filled",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val goal = goalText.toInt()

            val iconRes = when (binding.spinnerIcon.selectedItem.toString()) {
                "Fitness" -> R.drawable.ic_fitness
                "Study" -> R.drawable.ic_study
                "Drink" -> R.drawable.ic_drink
                "Meditation" -> R.drawable.ic_meditation

                else -> android.R.drawable.ic_menu_help
            }

            val habit = Habit(
                id = 0,
                name = name,
                description = desc,
                goal = goal,
                progress = 0,
                icon = iconRes,
                unit = unit
            )

            viewModel.insertHabit(habit)

            viewModel.clearForm()

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}