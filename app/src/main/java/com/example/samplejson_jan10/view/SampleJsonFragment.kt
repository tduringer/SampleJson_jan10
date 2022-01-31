package com.example.samplejson_jan10.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplejson_jan10.databinding.FragmentTodoBinding
import com.example.samplejson_jan10.model.network.ApiManager
import com.example.samplejson_jan10.model.network.models.Photo
import com.example.samplejson_jan10.model.network.models.Post
import com.example.samplejson_jan10.model.network.models.Todo
import com.example.samplejson_jan10.model.network.models.user.User
import com.example.samplejson_jan10.model.repository.SampleJsonRepository
import com.example.samplejson_jan10.utils.Resource
import com.example.samplejson_jan10.utils.SelectedData
import com.example.samplejson_jan10.utils.TAG
import com.example.samplejson_jan10.utils.showToast
import com.example.samplejson_jan10.view.adapter.PhotoAdapter
import com.example.samplejson_jan10.view.adapter.PostAdapter
import com.example.samplejson_jan10.view.adapter.TodoAdapter
import com.example.samplejson_jan10.view.adapter.UserAdapter
import com.example.samplejson_jan10.viewmodel.SampleJsonViewModel

class SampleJsonFragment : Fragment() {


    private var _binding: FragmentTodoBinding? = null
    private val binding: FragmentTodoBinding get() = _binding!!

    private val viewModel: SampleJsonViewModel by activityViewModels {
        SampleJsonViewModel.Factory(
            SampleJsonRepository(
                ApiManager()
            )
        )
    }

    private val todoAdapter by lazy {
        TodoAdapter()
    }

    private val postAdapter by lazy {
        PostAdapter()
    }

    private val userAdapter by lazy {
        UserAdapter()
    }

    private val photoAdapter by lazy {
        PhotoAdapter()
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
                Log.d(TAG,"todoBtn was pressed")
                viewModel.selectedDataType = SelectedData.TODOS
            }
            postBtn.setOnClickListener {
                Log.d(TAG,"postBtn was pressed")
                viewModel.selectedDataType = SelectedData.POSTS
            }
            userBtn.setOnClickListener {
                Log.d(TAG,"userBtn was pressed")
                viewModel.selectedDataType = SelectedData.USERS
            }
            photoBtn.setOnClickListener {
                Log.d(TAG,"photoBtn was pressed")
                viewModel.selectedDataType = SelectedData.PHOTOS
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
                            SelectedData.PHOTOS -> {
                                jsonRv.adapter = photoAdapter
                                photoAdapter.submitList(resource.data as List<Photo>)
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