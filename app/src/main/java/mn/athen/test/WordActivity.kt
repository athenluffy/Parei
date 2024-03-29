package mn.athen.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mn.athen.test.Adapter.WordListAdapter
import mn.athen.test.classes.Word
import mn.athen.test.viewmodel.WordViewModel
import mn.athen.test.viewmodel.WordViewModelFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance


class WordActivity : AppCompatActivity(), DIAware {
    private val TAG = "ViewModel"
    override val di: DI by closestDI()

    private val factory: WordViewModelFactory by instance()

    private lateinit var viewModel: WordViewModel
    private val getNewWord = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val word = Word(it.data!!.getStringExtra(NewWordActivity.EXTRA_REPLY)!!)
            viewModel.insert(word)
        } else {
            Toast.makeText(applicationContext, R.string.save_err, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val adapter = WordListAdapter(this.layoutInflater, null)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, factory)[WordViewModel::class.java]

        viewModel.words.observe(
            this
        ) {
            adapter.setWords(it)
            Log.d(TAG, "onCreate: " + it.size)

        }
        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder, target: ViewHolder
                ): Boolean {
                    return true // true if moved, false otherwise
                }
                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    val text =
                        viewHolder.itemView.findViewById<TextView>(R.id.textView).text.toString()
                    viewModel.deleteword(Word(text))
                }
            }).attachToRecyclerView(recyclerView)

        fab.setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            getNewWord.launch(intent)
        }


    }

}
