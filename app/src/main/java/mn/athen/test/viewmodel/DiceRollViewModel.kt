import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

//create a State of an object
data class DiceUiState(
    val firstDieValue: Int? =null,
    val secondDieValue:Int? = null,
    val numberofRolls: Int =0
)
//Implement View model
class DiceRollViewModel : ViewModel()
{
    //Expose screen UI State
    private val _uiState = MutableStateFlow(DiceUiState())
    val uiState : StateFlow<DiceUiState> = _uiState.asStateFlow()

    //when dice is rolled
    fun rollDice()
    {
        Log.d("DICE", "rollDice: ")
        _uiState.update { currentState ->
            currentState.copy(
                firstDieValue = Random.nextInt(1,7),
                secondDieValue = Random.nextInt(1,7),
                numberofRolls = currentState.numberofRolls+1
            )
        }
    }

}