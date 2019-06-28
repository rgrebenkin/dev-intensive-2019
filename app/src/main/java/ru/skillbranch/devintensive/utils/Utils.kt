package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)?.ifEmpty({null})
        val lastName = parts?.getOrNull(1)?.ifEmpty({null})
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?) : String? {
        val first : String? = firstName?.ifBlank { null }
        val last : String? = lastName?.ifBlank { null }

        return when {
            first == null && last == null -> return null
            first == null -> return last!![0].toUpperCase().toString()
            last == null -> return first[0].toUpperCase().toString()
            else -> first[0].toUpperCase().toString() + last[0].toUpperCase().toString()
        }
    }

    fun transliteration(payload: String, devider: String = " ") : String {
        val parts : List<String>? = payload.split(" ")
        var res = ""
        parts?.forEach({s: String ->
            run {
                if (!res.isBlank()) res += devider
                res += transliteString(s)
            }
        })
        return res
    }

    fun transliteString(payload: String) : String {
        var res = ""
        for (c in payload) {
            res += transliteChar(c)
        }
        return res
    }

    fun transliteChar(char: Char) : String {
        val s = when (char.toLowerCase()) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ' -> ""
            'ы' -> "i"
            'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            else -> char.toString()
        }
        return if (char.isUpperCase()) s.toUpperCase() else s
    }
}