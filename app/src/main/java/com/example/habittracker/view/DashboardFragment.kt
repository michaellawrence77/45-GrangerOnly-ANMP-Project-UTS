package com.example.habittracker.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habittracker.databinding.FragmentDashboardBinding
import com.example.habittracker.model.Habit

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val habitList = ArrayList<Habit>()
    private lateinit var adapter: HabitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        adapter = HabitListAdapter(habitList)
        binding.recHabit.layoutManager = LinearLayoutManager(context)
        binding.recHabit.adapter = adapter

        // dummy data sementara
        habitList.add(
            Habit("Minum Air", "Minum 8 gelas", 8, 2, 0)
        )
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}