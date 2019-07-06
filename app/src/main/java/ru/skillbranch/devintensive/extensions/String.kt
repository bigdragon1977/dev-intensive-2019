package ru.skillbranch.devintensive.extensions


    fun String.truncate(lengthNew: Int = 16): String {
        return when {
            lengthNew < this.trimEnd().length -> "${this.substring(0, lengthNew).trimEnd()}..."
            else -> this.trimEnd()
        }
    }