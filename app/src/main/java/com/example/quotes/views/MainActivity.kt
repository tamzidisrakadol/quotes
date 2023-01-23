package com.example.quotes.views

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.quotes.R
import com.example.quotes.model.Quotes
import com.example.quotes.viewModel.QuoteViewModel
import com.example.quotes.viewModel.QuoteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var quoteViewModel: QuoteViewModel
    lateinit var quoteText: TextView
    lateinit var authorText: TextView
    lateinit var prevBtn: TextView
    lateinit var nextBtn: TextView
    lateinit var shareBtn: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize the viewModel
        quoteViewModel =
            ViewModelProvider(this, QuoteViewModelFactory(application))[QuoteViewModel::class.java]
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
            if (quoteViewModel.index==0){
                Toast.makeText(this, "End of queue", Toast.LENGTH_SHORT).show()
            }else{
                setQuote(quoteViewModel.prevquote())
            }

        }

        shareBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, quoteViewModel.getQuote().text)
            startActivity(intent)
        }


        val onBackPress = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("EXIT")
                alertDialog.setMessage("Do you want to close the application ?")
                alertDialog.setPositiveButton("OK") { alertDialog,
                                                      _ -> finish() }
                val alert = alertDialog.create()
                alert.show()
            }
        }

        onBackPressedDispatcher.addCallback(onBackPress)
    }

    private fun setQuote(quote: Quotes) {
        quoteText.text = quote.text
        authorText.text = quote.author
    }

}