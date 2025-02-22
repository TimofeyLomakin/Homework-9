package ru.netology

data class Chats(
    val chatId: Int,
    val userId: Int,
    val messages: MutableList<Messages>
)

data class Messages(
    val userIdMessage: Int,
    var textMessage: String,
    val idMessage: Int,
    var unread: Boolean
)

class DirectMessages {
    val chats = mutableListOf<Chats>()

    fun deleteChat(chatId: Int): Boolean {
        val findChat = chats.find { it.chatId == chatId } ?: throw IllegalArgumentException("Чат не найден")
        findChat.messages.clear()
        chats.remove(findChat)
        return true
    }

    fun getAllChats(): List<Chats> {
        return chats.toList()
    }

    fun createMessage(chatId: Int, messages: Messages, userId: Int): Boolean {

        val newMessage = Messages(
            userIdMessage = messages.userIdMessage,
            textMessage = messages.textMessage,
            idMessage = messages.idMessage,
            unread = messages.unread
        )
        val createChat = chats.find { it.chatId == chatId }
        if (createChat == null) {
            chats.add(
                Chats(
                    chatId = chatId,
                    userId = userId,
                    messages = mutableListOf(newMessage)
                )
            )
        } else {
            createChat.messages.add(newMessage)

        }
        return true
    }

    fun deleteMessage(chatId: Int, idMessage: Int): Boolean {
        val findChat = chats.find { it.chatId == chatId } ?: throw IllegalArgumentException("Чат не найден")
        val findMessage = findChat.messages.find { it.idMessage == idMessage }
            ?: throw IllegalArgumentException("Сообщение не найдено")
        findChat.messages.remove(findMessage)
        return true
    }

    fun getChatMessages(chatId: Int): List<Messages> {
        return chats.asSequence()
        .filter { it.chatId == chatId }
            .flatMap { it.messages.asSequence() }
        .onEach { it.unread = false }
            .toList()
    }

    fun getChatSomeMessages(chatId: Int, userIdMessage: Int, countMessages: Int): List<Messages> {
        return chats.asSequence()
            .filter { it.chatId == chatId }
            .flatMap { it.messages.asSequence() }
            .filter { it.userIdMessage == userIdMessage }
            .toList()
            .takeLast(countMessages)
            .onEach { it.unread = false }
    }

    fun getMessagesToUser(chatId: Int, userIdMessage: Int): List<Messages> {
        return chats.asSequence()
            .filter { it.chatId == chatId }
            .flatMap { it.messages.asSequence() }
            .filter { it.userIdMessage == userIdMessage }
            .toList()
    }

    fun getLastMessages(): List<String> {
        return chats.asSequence()
            .map { it.messages.lastOrNull()?.textMessage ?: "Нет сообщений" }
            .toList()
    }

    fun getUnreadChatsCount(): Int {
        return chats.count { chat -> chat.messages.any { !it.unread } }
    }
}


fun main() {

}