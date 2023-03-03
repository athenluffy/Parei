package mn.athen.test

import android.app.Application
import mn.athen.test.db.WordDatabase
import mn.athen.test.repository.WordRepository
import mn.athen.test.viewmodel.WordViewModelFactory
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule


class App : Application(),DIAware
{
    override val di by DI.lazy{
        import(androidXModule(this@App))

        bind { singleton { WordDatabase(instance()) }}
        bind{singleton { WordRepository(instance()) }}
        bind{singleton { WordViewModelFactory(instance()) }}

    }
}

