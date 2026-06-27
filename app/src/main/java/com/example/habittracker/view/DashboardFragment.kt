package com.example.habittracker.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HabitViewModel by activityViewModels()

    private lateinit var adapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HabitListAdapter(arrayListOf()) {
            viewModel.updateHabit(it)
        }

        binding.recHabit.layoutManager = LinearLayoutManager(requireContext())
        binding.recHabit.adapter = adapter

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addHabitFragment)
        }

        viewModel.habitList.observe(viewLifecycleOwner) { list ->
            adapter.updateData(ArrayList(list))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}