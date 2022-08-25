package com.zemoga.postviewer.data

import com.zemoga.postviewer.data.environments.Environment

class DataModule {

    companion object {
        fun returnBaseUrl() : Environment {
            return Environment("https://jsonplaceholder.typicode.com/")
        }
    }
}