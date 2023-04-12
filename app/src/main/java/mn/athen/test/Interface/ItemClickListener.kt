package mn.athen.test.Interface

import mn.athen.test.classes.Item

interface ItemClickListener {

    fun onclick(position: Int,item :Item)
}