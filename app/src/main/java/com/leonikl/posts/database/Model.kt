package com.leonikl.posts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
class Page {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "page")
    var page: String = "Hello!"

    @ColumnInfo(name = "pass")
    var password: String = "0000"

    @ColumnInfo(name = "state")
    var state: Boolean = false

    constructor()
    constructor(id: Int){
        this.id = id
    }
    constructor(id: Int, page: String, password: String, state: Boolean) {
        this.id = id
        this.page = page
        this.password = password
        this.state = state
    }
    constructor(page: String, password: String, state: Boolean) {
        this.page = page
        this.password = password
        this.state = state
    }
}