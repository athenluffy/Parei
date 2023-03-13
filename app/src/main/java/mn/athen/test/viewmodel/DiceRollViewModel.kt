import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)
//Implement View model
class DiceRollViewModel : ViewModel()
{
    private val _DiceUiState = MutableStateFlow( DiceUiState())

    val uiState : StateFlow<DiceUiState> = _DiceUiState.asStateFlow()

    fun rollDice() {
        _DiceUiState.update {
            it.copy(
                firstDieValue = Random.nextInt(1,7),
                secondDieValue = Random.nextInt(1,7),
                numberOfRolls=it.numberOfRolls+1

            )
        }
        }

}