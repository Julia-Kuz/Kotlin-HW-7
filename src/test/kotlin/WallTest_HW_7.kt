import org.junit.Assert.*
import org.junit.Test

class WallTestHW7 {

    @Test
    fun add() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())

        val attachments = arrayOf(audio, photo, document, note, video)

        val addWall = Wall()
        val postTest1 = Post(
            10, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        val result = addWall.add(postTest1)
        val expected: Boolean = result.id != 0

        return assertTrue(expected)
    }

    @Test
    fun updateExisting() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())

        val attachments = arrayOf(audio, photo, document, note, video)
        // создаём целевой сервис
        val updateWall = Wall()
        // заполняем несколькими постами
        updateWall.add(
            Post(
                1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        updateWall.add(
            Post(
                2, 11, 111, 1111, 11111, "2", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        updateWall.add(
            Post(
                3, 11, 111, 1111, 11111, "3", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        // создаём информацию об обновлении
        val postForUpdate = Post(
            1, 11, 111, 1111, 11111, "4", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        // выполняем целевое действие
        val result = updateWall.update(postForUpdate)
        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }

    @Test
    fun updateNonExisting() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())

        val attachments = arrayOf(audio, photo, document, note, video)
        // создаём целевой сервис
        val updateWall = Wall()
        // заполняем несколькими постами
        updateWall.add(
            Post(
                1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        updateWall.add(
            Post(
                2, 11, 111, 1111, 11111, "2", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        updateWall.add(
            Post(
                3, 11, 111, 1111, 11111, "3", 111111, 1111111, false, "reply", 11111111,
                canPin = true,
                canDelete = true,
                canEdit = true,
                isPinned = true,
                markedAsAds = false,
                isFavourite = false,
                postponedId = 111111111,
                attachments = attachments
            )
        )
        // создаём информацию об обновлении
        val postForUpdate = Post(
            5, 11, 111, 1111, 11111, "4", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        // выполняем целевое действие
        val result = updateWall.update(postForUpdate)
        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    //                                         ЗАДАНИЕ 7.2
    @Test
    fun createCommentShouldAddId() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())
        val attachments = arrayOf(audio, photo, document, note, video)
        val postTest1 = Post(
            1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )
        val commentWall = Wall()
        commentWall.add(postTest1)

        val commentForCheck = Wall.Comment()
        val idForCheck = 1

        val result = commentWall.createComment(idForCheck, commentForCheck)
        val expected: Boolean = result.id == idForCheck

        return assertTrue(expected)
    }

    @Test(expected = Wall.PostNotFoundException::class)
    fun createCommentShouldThrowException() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())
        val attachments = arrayOf(audio, photo, document, note, video)
        val postTest1 = Post(
            1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )
        val commentWall = Wall()
        commentWall.add(postTest1)

        val commentForCheck = Wall.Comment()
        val idForCheck = 2

        commentWall.createComment(idForCheck, commentForCheck)
    }

    //                                                       ЗАДАНИЕ 7.3

    @Test
    fun createComplaintShouldAdd() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())
        val attachments = arrayOf(audio, photo, document, note, video)
        val postTest1 = Post(
            1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        val complaintWall = Wall()
        complaintWall.add(postTest1)
        val comment1 = Wall.Comment(1, 2)
        complaintWall.createComment(1, comment1)

        val result = complaintWall.createComplaint(Wall.Complaint(2,1, 3))

        return assertEquals(1, result)
    }

    @Test(expected = Wall.CommentNotFoundException::class)
    fun createComplaintShouldThrowExceptionComment() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())
        val attachments = arrayOf(audio, photo, document, note, video)
        val postTest1 = Post(
            1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        val complaintWall = Wall()
        complaintWall.add(postTest1)
        val comment1 = Wall.Comment(1, 2)
        complaintWall.createComment(1, comment1)

        complaintWall.createComplaint(Wall.Complaint(2,2, 3))

    }

    @Test(expected = Wall.ReasonNotFoundException::class)
    fun createComplaintShouldThrowExceptionReason() {
        val audio = AudioAttachment(Audio())
        val photo = PhotoAttachment(Photo())
        val document = DocumentAttachment(Document())
        val note = NoteAttachment(Note())
        val video = VideoAttachment(Video())
        val attachments = arrayOf(audio, photo, document, note, video)
        val postTest1 = Post(
            1, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
            canPin = true,
            canDelete = true,
            canEdit = true,
            isPinned = true,
            markedAsAds = false,
            isFavourite = false,
            postponedId = 111111111,
            attachments = attachments
        )

        val complaintWall = Wall()
        complaintWall.add(postTest1)
        val comment1 = Wall.Comment(1, 2)
        complaintWall.createComment(1, comment1)

        complaintWall.createComplaint(Wall.Complaint(2,1, 9))

    }





}

