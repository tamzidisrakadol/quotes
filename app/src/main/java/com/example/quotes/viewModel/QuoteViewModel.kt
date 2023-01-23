package com.example.quotes.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quotes.model.Quotes
import com.google.gson.Gson

class QuoteViewModel(val context:Context): ViewModel() {

    //create an empty array
    private var quoteList:Array<Quotes> = emptyArray()
    private var index = 0


    init {
        quoteList = loadQuoteFromAsset()
    }

    //loading quote
    private fun loadQuoteFromAsset():Array<Quotes>{

        //open the json file
        val inputStream = context.assets.open("quoteList.json")
        //define the size
        val size:Int = inputStream.available()
        //store the file
        val buffer = ByteArray(size)
        //read the file
        inputStream.read(buffer)
        //close
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)

    }

    fun getQuote()=quoteList[index]

    fun nextQuote() =quoteList[(++index + quoteList.size) % quoteList.size]
    fun prevquote()=quoteList[(--index + quoteList.size) % quoteList.size]



}