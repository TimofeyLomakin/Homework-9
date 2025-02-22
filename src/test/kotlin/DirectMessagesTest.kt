import org.junit.Assert.*
import ru.netology.Chats
import ru.netology.DirectMessages
import ru.netology.Messages
import kotlin.test.Test

class DirectMessagesTest {

    @Test
    fun deleteChat() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        assertTrue(directMessages.deleteChat(1))
        assertEquals(0, directMessages.getAllChats().size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteChatIllegalArgumentException() {
        val directMessages = DirectMessages()
        directMessages.deleteChat(1)
    }

    @Test
    fun getAllChats() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        assertEquals(1, directMessages.getAllChats().size)
    }

    @Test
    fun createMessage() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        val createMessage = directMessages.createMessage(1, Messages(1, "Новое сообщение", 2, true), 1)
        assertTrue(createMessage)
        assertEquals(2, directMessages.getChatMessages(1).size)
    }

    @Test
    fun createChat() {
        val directMessages = DirectMessages()
        val createChat = directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        assertTrue(createChat)
    }

    @Test
    fun deleteMessage() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 2, true), 1)
        assertTrue(directMessages.deleteMessage(1, 2))
        assertEquals(1, directMessages.getChatMessages(1).size)
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteMessageIllegalArgumentExceptionToChat() {
        val directMessages = DirectMessages()
        directMessages.deleteMessage(1, 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun deleteMessageIllegalArgumentExceptionToMessage() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        directMessages.deleteMessage(1, 2)
    }

    @Test
    fun getChatMessages() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение 1", 1, true), 1)
        directMessages.createMessage(1, Messages(1, "Новое сообщение 2", 2, true), 1)
        assertEquals(2, directMessages.getChatMessages(1).size)
    }

    @Test
    fun getMessagesToUser() {
        val directMessages = DirectMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение", 1, true), 1)
        assertEquals(1, directMessages.getMessagesToUser(1, 1).size)
    }



    @Test
    fun getLastMessages() {
        val directMessages = DirectMessages()
        directMessages.getLastMessages()
        assertEquals(0, directMessages.getLastMessages().size)
        directMessages.createMessage(1, Messages(1, "Новое сообщение1", 1, true), 1)
        assertEquals(1, directMessages.getLastMessages().size)
        directMessages.createMessage(1, Messages(1, "Новое сообщение2", 2, true), 1)
        assertEquals(1, directMessages.getLastMessages().size)
        directMessages.createMessage(2, Messages(1, "Новое сообщение3", 1, true), 1)
        assertEquals(2, directMessages.getLastMessages().size)
    }

    @Test
    fun getUnreadChatsCount() {
        val directMessages = DirectMessages()
        directMessages.getLastMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение1", 1, true), 1)
        assertEquals(0, directMessages.getUnreadChatsCount())
        directMessages.createMessage(1, Messages(1, "Новое сообщение1", 1, false), 1)
        assertEquals(1, directMessages.getUnreadChatsCount())
    }

    @Test
    fun getChatSomeMessages() {
        val directMessages = DirectMessages()
        directMessages.getLastMessages()
        directMessages.createMessage(1, Messages(1, "Новое сообщение1", 1, true), 1)
        directMessages.createMessage(1, Messages(2, "Новое сообщение2", 2, true), 1)
        directMessages.createMessage(1, Messages(1, "Новое сообщение3", 3, true), 1)
        directMessages.createMessage(1, Messages(2, "Новое сообщение4", 4, true), 1)
        assertEquals(2, directMessages.getChatSomeMessages(1, 1, 2).size)
    }

}