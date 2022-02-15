package com.example.samplejson_jan10.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplejson_jan10.databinding.FragmentTodoBinding
import com.example.samplejson_jan10.model.network.models.Post
import com.example.samplejson_jan10.model.network.models.Todo
import com.example.samplejson_jan10.model.network.models.user.User
import com.example.samplejson_jan10.utils.Resource
import com.example.samplejson_jan10.utils.SelectedData
import com.example.samplejson_jan10.utils.showToast
import com.example.samplejson_jan10.view.adapter.PostAdapter
import com.example.samplejson_jan10.view.adapter.TodoAdapter
import com.example.samplejson_jan10.view.adapter.UserAdapter
import com.example.samplejson_jan10.viewmodel.SampleJsonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleJsonFragment : Fragment() {

    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private val viewModel: SampleJsonViewModel by activityViewModels()

    private val todoAdapter by lazy {
        TodoAdapter()
    }

    private val postAdapter by lazy {
        PostAdapter()
    }

    private val userAdapter by lazy {
        UserAdapter()
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
        with(binding) {
            jsonRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }

            // Click Listeners
            todoBtn.setOnClickListener {
                viewModel.selectedDataType = SelectedData.TODOS
            }
            postBtn.setOnClickListener {
                viewModel.selectedDataType = SelectedData.POSTS
            }
            userBtn.setOnClickListener {
                viewModel.selectedDataType = SelectedData.USERS
            }

            viewModel.data.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.isVisible = true
                        btnGroup.isEnabled = false
                    }
                    is Resource.Success -> {
                        progressBar.isVisible = false
                        btnGroup.isEnabled = true
                        when (viewModel.selectedDataType) {
                            SelectedData.TODOS -> {
                                jsonRv.adapter = todoAdapter
                                todoAdapter.submitList(resource.data as List<Todo>)
                            }
                            SelectedData.POSTS -> {
                                jsonRv.adapter = postAdapter
                                postAdapter.submitList(resource.data as List<Post>)
                            }
                            else -> {
                                jsonRv.adapter = userAdapter
                                userAdapter.submitList(resource.data as List<User>)
                            }
                        }
                    }
                    is Resource.Error -> {
                        progressBar.isVisible = false
                        btnGroup.isEnabled = true
                        showToast(resource.message)
                    }
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}