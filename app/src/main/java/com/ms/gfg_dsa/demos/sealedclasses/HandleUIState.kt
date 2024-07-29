package com.ms.gfg_dsa.demos.sealedclasses


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class HandleUIState {
    object Loading : HandleUIState()
    class Success(list: List<String>) : HandleUIState()
    class Error(errorMessage: String) : HandleUIState()
}


class MyViewModel : ViewModel() {
    private val _uiState = MutableLiveData<HandleUIState>()
    val uiState: LiveData<HandleUIState> get() = _uiState

    fun fetchData() {
        _uiState.value = HandleUIState.Loading
        viewModelScope.launch {
            try {
//                val data = repository.getData()
                _uiState.value = HandleUIState.Success(arrayListOf())
            } catch (e: Exception) {
                _uiState.value = HandleUIState.Error("Unknown Error")
            }
        }
    }
}

class AFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MyViewModel by viewModels()
        viewModel.fetchData()

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HandleUIState.Loading -> showLoading()
                is HandleUIState.Success -> showData(arrayListOf())
                is HandleUIState.Error -> showError()
            }
        }
    }

    private fun showLoading() {}
    private fun showError() {}
    private fun showData(list: List<String>) {}
}

sealed class ViewState {
    class Loading(val onBackPressed: () -> Unit = {}) : ViewState()

    class Content(val event: Int) : ViewState()

    class Error(val error: String) : ViewState()
}