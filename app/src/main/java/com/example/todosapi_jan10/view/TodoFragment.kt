package com.example.todosapi_jan10.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todosapi_jan10.databinding.FragmentTodoBinding
import com.example.todosapi_jan10.model.network.ApiManager
import com.example.todosapi_jan10.model.repository.TodoRepository
import com.example.todosapi_jan10.viewmodel.TodoViewModel

class TodoFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private val viewModel: TodoViewModel by activityViewModels {
        TodoViewModel.Factory(TodoRepository(ApiManager()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.todos.observe(viewLifecycleOwner) {
            binding.todoTv.text = it.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}