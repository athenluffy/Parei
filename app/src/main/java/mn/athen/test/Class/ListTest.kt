package mn.athen.test.Class

import mn.athen.test.R

class ListTest {
    var list: MutableList<Item> = ArrayList()

    init {
        val item = Item("Chicken Pickle", R.drawable.splash, 5)
        list.add(item)
    }
}