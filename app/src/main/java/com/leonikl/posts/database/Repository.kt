package com.leonikl.posts.database

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PageRepository(private val pageDao: PageDao) {
    val allPages: LiveData<List<Page>> = pageDao.getAllPages()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertPage(newPage: Page) {
        coroutineScope.launch(Dispatchers.IO) {
            pageDao.insertPage(newPage)
        }
    }

    fun deletePage(page: Page) {
        coroutineScope.launch(Dispatchers.IO) {
            pageDao.deletePage(page)
        }
    }

    fun updatePage(page: Page){
        coroutineScope.launch(Dispatchers.IO) {
            pageDao.updatePage(page)
        }
    }
}
