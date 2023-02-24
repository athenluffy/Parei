package mn.athen.test

import DiceRollViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DiceRollActivity : AppCompatActivity() {

    private lateinit var viewModel: DiceRollViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
/*        val viewModel: DiceRollViewModel by viewModels() {
            lifecycleScope.launch(
                repeatOnLifecycle(Lifecycle.State.STARTED)
                {
                    viewModel.uiState.collect{

                    }
                }
            )
        }*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_roll)

        viewModel = ViewModelProvider(this).get(DiceRollViewModel::class.java)

        viewModel.rollDice()


    }
}
