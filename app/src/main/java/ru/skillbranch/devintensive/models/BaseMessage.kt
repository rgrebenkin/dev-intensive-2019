package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage() : String

    companion object AbstractFactory {
        private var lastId: Int = -1

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String="text", payload: Any?, isIncoming: Boolean = false) : BaseMessage {
            lastId++
            return when (type) {
                "text" -> TextMessage("$lastId", from, chat, text = payload as String, date = date, isIncoming = isIncoming)
                else -> ImageMessage("$lastId", from, chat, image = payload as String, date = date, isIncoming = isIncoming)
            }
        }
    }
}