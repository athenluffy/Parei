package mn.athen.test

import android.app.Application
import mn.athen.test.db.WordRoomDatabase
import org.kodein.di.*


val di= DI{

bind{ singleton { WordRoomDatabase(instance()) }}
}



class App(override val di: DI) : Application(),DIAware

fun main() { App(di) }