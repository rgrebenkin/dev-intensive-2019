package ru.skillbranch.devintensive.models

import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import java.util.*

class TextMessage(
    id: String,
    from: User,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage(): String {
        return "$id ${from!!.firstName} ${from.lastName} ${if(isIncoming) "получил" else "отправил"} сообщение \"$text\""
    }
}