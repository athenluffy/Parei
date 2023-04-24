package mn.athen.test.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_cart")
class Cart (@field:ColumnInfo(name = "id") @field:PrimaryKey val id:Int,
            @field:ColumnInfo(name = "itemid")
            val item_id:Int,
            @field:ColumnInfo(name="count")
            val count:Int
           )