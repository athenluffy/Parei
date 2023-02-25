package mn.athen.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mn.athen.test.Adapter.WordListAdapter
import mn.athen.test.Class.Word
import mn.athen.test.viewmodel.WordViewModel


class DiceRollActivity : AppCompatActivity() {
    companion object
    {
        const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    }
    val TAG ="ViewModel"

    private lateinit var viewModel: WordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val adapter = WordListAdapter(this.layoutInflater,null)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel = ViewModelProvider(this)[WordViewModel::class.java]

        viewModel.words.observe(this
        ) {
            adapter.setWords(it)
            Log.d(TAG, "onCreate: " + it.size)

        }
        fab.setOnClickListener{
            val intent = Intent(this,NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }


        //viewModel.getLoggedInUser().







    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode== RESULT_OK)
        {
            val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY)!!)
            viewModel.insert(word)
        }
        else
        {
            Toast.makeText(applicationContext,R.string.save_err,Toast.LENGTH_LONG).show()
        }
    }
}
