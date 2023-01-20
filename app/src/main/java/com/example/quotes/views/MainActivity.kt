package com.example.quotes.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.R
import com.example.quotes.model.Quotes
import com.example.quotes.viewModel.QuoteViewModel
import com.example.quotes.viewModel.QuoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var quoteViewModel:QuoteViewModel
    lateinit var quoteText:TextView
    lateinit var authorText:TextView
    lateinit var prevBtn:TextView
    lateinit var nextBtn:TextView
    lateinit var shareBtn:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize the viewModel
        quoteViewModel = ViewModelProvider(this,QuoteViewModelFactory(application))[QuoteViewModel::class.java]
        quoteText = findViewById(R.id.quoteText)
        authorText = findViewById(R.id.authorText)
        prevBtn = findViewById(R.id.prevBtn)
        nextBtn = findViewById(R.id.nextBtn)
        shareBtn = findViewById(R.id.shareBtn)

        //setting the quote from ViewModel
        setQuote(quoteViewModel.getQuote())

        nextBtn.setOnClickListener {
            setQuote(quoteViewModel.nextQuote())
        }


        prevBtn.setOnClickListener {
            setQuote(quoteViewModel.prevquote())
        }

        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,quoteViewModel.getQuote().text)
            startActivity(intent)
        }
    }

    private fun setQuote(quote:Quotes){
        quoteText.text=quote.text
        authorText.text=quote.author
    }




}