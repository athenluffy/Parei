package mn.athen.test.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_items")
class Item(
    @field:ColumnInfo(name = "id") @field:PrimaryKey val id:Int,
    @field:ColumnInfo(name = "name")
    val name:String,
    @field:ColumnInfo(name="img")
    val img:String,
    @field:ColumnInfo(name="rating")
    val star:Float)
