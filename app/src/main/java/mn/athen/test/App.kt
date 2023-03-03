package mn.athen.test

import android.app.Application
import mn.athen.test.db.WordDatabase
import mn.athen.test.repository.WordRepository
import mn.athen.test.viewmodel.WordViewModelFactory
import org.kodein.di.*


val di= DI{

    bind{ singleton { WordDatabase(instance()) }}
    bind{singleton { WordRepository(instance()) }}
    bind{provider { WordViewModelFactory(instance()) }}

}



class App(override val di: DI) : Application(),DIAware

fun main() { App(di) }