package com.github.sectionedrecyclersample.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.sectionedrecyclersample.databinding.FragmentToDoListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoListFragment : Fragment() {

    private var _binding: FragmentToDoListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToDoListBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }
}