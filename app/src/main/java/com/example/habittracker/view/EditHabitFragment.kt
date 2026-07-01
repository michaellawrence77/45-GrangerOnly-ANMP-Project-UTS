package com.example.habittracker.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentEditHabitBinding
import com.example.habittracker.model.Habit
import com.example.habittracker.viewmodel.HabitViewModel

class EditHabitFragment : Fragment() {

    private var _binding: FragmentEditHabitBinding? = null
    private val binding get() = _binding!!

    private val args: EditHabitFragmentArgs by navArgs()

    private val viewModel: HabitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_habit,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val habit = args.habit

        val iconList = arrayOf(
            "Fitness",
            "Study",
            "Drink",
            "Meditation"
        )

        binding.spinnerIcon.adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                iconList
            )

        binding.etName.setText(habit.name)
        binding.etDesc.setText(habit.description)
        binding.etGoal.setText(habit.goal.toString())
        binding.etUnit.setText(habit.unit)

        binding.spinnerIcon.setSelection(
            when (habit.icon) {
                R.drawable.ic_fitness -> 0
                R.drawable.ic_study -> 1
                R.drawable.ic_drink -> 2
                R.drawable.ic_meditation -> 3
                else -> 0
            }
        )

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSave.setOnClickListener {

            val iconRes = when (binding.spinnerIcon.selectedItemPosition) {
                0 -> R.drawable.ic_fitness
                1 -> R.drawable.ic_study
                2 -> R.drawable.ic_drink
                else -> R.drawable.ic_meditation
            }

            val updatedHabit = Habit(
                id = habit.id,
                name = binding.etName.text.toString(),
                description = binding.etDesc.text.toString(),
                goal = binding.etGoal.text.toString().toInt(),
                progress = habit.progress,
                icon = iconRes,
                unit = binding.etUnit.text.toString()
            )

            viewModel.updateHabit(updatedHabit)

            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}