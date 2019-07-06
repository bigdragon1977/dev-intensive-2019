package ru.skillbranch.devintensive.utils


object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(delimiters = *arrayOf(" "))
        val firstName = when {
            parts?.getOrNull(index = 0).isNullOrBlank() -> null
            parts?.getOrNull(index = 0).isNullOrEmpty() -> null
            else -> parts?.getOrNull(index = 0)
        }
        val lastName = when {
            parts?.getOrNull(index = 1).isNullOrBlank() -> null
            parts?.getOrNull(index = 1).isNullOrEmpty() -> null
            else -> parts?.getOrNull(index = 1)
        }
        return Pair(firstName, lastName)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var result = payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюя]")) {
            when (it.value) {
                "а"->"a"
                "б"->"b"
                "в"->"v"
                "г"->"g"
                "д"->"d"
                "е"->"e"
                "ё"->"e"
                "ж"->"zh"
                "з"->"z"
                "и"->"i"
                "й"->"i"
                "к"->"k"
                "л"->"l"
                "м"->"m"
                "н"->"n"
                "о"->"o"
                "п"->"p"
                "р"->"r"
                "с"->"s"
                "т"->"t"
                "у"->"u"
                "ф"->"f"
                "х"->"h"
                "ц"->"c"
                "ч"->"ch"
                "ш"->"sh"
                "щ"->"sh'"
                "ъ"->""
                "ы"->"i"
                "ь"->""
                "э"->"e"
                "ю"->"yu"
                "я"->"ya"
                else -> it.value
            }

        }
        return result.replace(" ", divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return when {
            firstName.isNullOrEmpty() and lastName.isNullOrEmpty() -> null
            firstName.isNullOrBlank() and lastName.isNullOrBlank() -> null
            lastName.isNullOrBlank() -> firstName?.take(1)
            else -> "${firstName?.take(1)}${lastName?.take(1)}"
        }
    }
}