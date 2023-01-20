package com.example.quotes.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//when we want to pass parameter in ViewModel we use ViewModelFactory
class QuoteViewModelFactory(val context:Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(context) as T
    }

}