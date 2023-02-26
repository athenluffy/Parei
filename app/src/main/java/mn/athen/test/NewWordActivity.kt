package mn.athen.test

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewWordActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_REPLY = "mn.athen.test.REPLY"
    }

    private lateinit var wordView: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        wordView=findViewById(R.id.edit_word)

        val btn:Button = findViewById(R.id.btn_save)

        btn.setOnClickListener {
            val intent = Intent()
            if(TextUtils.isEmpty(wordView.text))
            {
                setResult(RESULT_CANCELED,intent)
            }
            else
            {
                val word = wordView.text.toString()
                intent.putExtra(EXTRA_REPLY,word)
                setResult(RESULT_OK,intent)
            }
            finish()

        }
    }

}