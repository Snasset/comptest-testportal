package com.dheril.intern_test.screen.thirdscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.dheril.intern_test.ViewModelFactory
import com.dheril.intern_test.data.Repository
import com.dheril.intern_test.databinding.ActivityThirdscreenBinding

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdscreenBinding
    private lateinit var userListViewModel: ThirdScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = Repository()
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val factory = ViewModelFactory(repository)
        userListViewModel = ViewModelProvider(this, factory).get(ThirdScreenViewModel::class.java)
        getData()
        setupviews()
    }

    private fun setupviews(){
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.layoutRefresh.setOnRefreshListener {
            getData()
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun getData() {
        val adapter = ThirdScreenAdapter()
        binding.rvUsers.adapter = adapter

        userListViewModel.listUser.observe(this, { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        })

        adapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.Loading) {
                binding.layoutRefresh.isRefreshing = true
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.layoutRefresh.isRefreshing = false
                val isEmptyList = adapter.itemCount == 0
                binding.tvEmpty.visibility = if (isEmptyList) View.VISIBLE else View.GONE
            }


            val loadError = when {
                loadState.source.append is LoadState.Error -> loadState.source.append as LoadState.Error
                loadState.source.prepend is LoadState.Error -> loadState.source.prepend as LoadState.Error
                loadState.source.refresh is LoadState.Error ->
                    loadState.source.refresh as LoadState.Error
                else -> null
            }

            loadError?.let {
                Toast.makeText(this, "Error: ${it.error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}