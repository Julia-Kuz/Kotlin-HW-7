data class Post(
    var id: Int?,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String?,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val postType: String,
    val signerId: Int,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavourite: Boolean,
    val postponedId: Int,
    val comment: Comments? = Comments(),
    val copyright: Copyright = Copyright(),
    val like: Likes? = Likes(),
    val reposts: Reposts? = Reposts(),
    val views: Views? = Views(),
    val geo: Geo = Geo(),
    val donut: Donut = Donut(),
    val attachments: Array<Attachments>
)


data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val cavOpen: Boolean = true
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = true,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Copyright(
    val id: Int = 1,
    val link: String = "netology.ru",
    val name: String = "Netology",
    val type: String = "reference"
)

data class Reposts(
    val counter: Int = 0,
    val userReposted: Int = 0
)

data class Views(
    val count: Int = 0
)

data class Geo(
    val type: String = "Moscow",
    val coordinates: String = " 55.7522, 37.6156",

    )

data class Donut(
    val isDonut: Boolean = false,
    val paidDuration: Int = 0,
    val canPublishFreeCopy: Boolean = false,
    val editMode: String = "all"
)

//******************************************************  ATTACHMENTS **************************************************
sealed class Attachments(val type: String)

data class AudioAttachment(private val audio: Audio) : Attachments("Audio") {
    override fun toString(): String {
        return type + audio
    }
}

data class Audio(
    val id: Int = 1,
    val ownerId: Int = 1,
    val artist: String = "artist1",
    val title: String = "title1",
    val duration: Int = 5,
    val url: String = "www.url",
    val lyricsId: Int = 1,
    val albumId: Int = 1,
    val genreId: Int = 1,
    val date: Int = 111123,
    val noSearch: Int = 1,
    val isHq: Int = 1
)

data class PhotoAttachment(private val photo: Photo) : Attachments("Photo") {
    override fun toString(): String {
        return type + photo
    }
}

data class Photo(
    val id: Int = 2,
    val ownerId: Int = 2,
    val albumId: Int = 2,
    val userId: Int = 2,
    val text: String = "photo1",
    val date: Int = 222222,
    val width: Int = 2222,
    val height: Int = 2222,
    val sizes: Array<Copy> = arrayOf(Copy())
)

data class Copy(
    val type: String = "type",
    val url: String = "url",
    val width: Int = 10,
    val height: Int = 15
)

data class DocumentAttachment(private val document: Document) : Attachments("Document") {
    override fun toString(): String {
        return type + document
    }
}

data class Document(
    val id: Int = 3,
    val ownerId: Int = 3,
    val title: String = "title Doc",
    val size: Int = 3,
    val ext: String = "doc",
    val url: String = "url",
    val date: Int = 33333,
    val type: Int = 1,
)

data class NoteAttachment(private val note: Note) : Attachments("Note") {
    override fun toString(): String {
        return type + note
    }
}

data class Note(
    val id: Int = 4,
    val ownerId: Int = 4,
    val title: String = "title Note",
    val text: String = "text Note",
    val date: Int = 44444,
    val comments: Int = 4,
    val readComments: Int = 4,
    val viewUrl: String = "view url",
    val privacyView: String = "public",
    val privacyComment: String = "public",
    val canComment: Boolean = true,
    val textWiki: String = "text_wiki"
)

data class VideoAttachment(private val video: Video) : Attachments("Video") {
    override fun toString(): String {
        return type + video
    }
}

data class Video(
    val id: Int = 5,
    val ownerId: Int = 5,
    val title: String = "title video",
    val description: String = "description video",
    val duration: Int = 5,
    val views: Int = 5,
    val date: Int = 55555,
    val addingDate: Int = 55,
    val localViews: Int = 5,
    val comments: Int = 5
)


//******************************************************  WALL SERVICE **************************************************

class Wall {

    private var posts = emptyArray<Post>()
    private var idUnique = 1

    fun add(post: Post): Post {
        posts += post.copy(
            id = idUnique++,
            comment = post.comment?.copy(),
            copyright = post.copyright.copy(),
            like = post.like?.copy(),
            reposts = post.reposts?.copy(),
            views = post.views?.copy(),
            geo = post.geo.copy(),
            donut = post.donut.copy(),
            attachments = post.attachments.copyOf()
        )
        return posts.last()

    }
    //                                         ЗАДАНИЕ 7.1

    private var comments = emptyArray<Comment>()

    class PostNotFoundException(message: String) : RuntimeException(message)

    fun createComment(postId: Int, comment: Comment): Comment {
        for (post in posts) {
            if (postId == post.id) {
                comments += comment.copy(attachments = comment.attachments.copyOf())
                return comments.last()
            }
        }
        throw PostNotFoundException("Пост с id $postId не существует")
    }

    data class Comment(
        val id: Int = 1,
        val fromId: Int = 5,
        val date: Int = 555,
        val text: String = "comment 1",
        val attachments: Array<Attachments> = arrayOf(AudioAttachment(Audio()), PhotoAttachment(Photo()))
    )

    //                                         ЗАДАНИЕ 7.3

    private var complaints = emptyArray<Complaint>()

    data class Complaint(
        val ownerId: Int,
        val commentId: Int,
        val reason: Int
    )

    class CommentNotFoundException(message: String) : RuntimeException(message)
    class ReasonNotFoundException(message: String) : RuntimeException(message)

    fun createComplaint(complaint: Complaint): Int {

        if (complaint.reason in 0..8) {
            for (c in comments) {
                if (complaint.commentId == c.id) {
                    complaints += complaint.copy()
                    println("Жалоба будет рассмотрена")
                    return 1
                }
            }
            throw CommentNotFoundException("Невозможно найти данный комментарий")
        }
        throw ReasonNotFoundException("Некорректная причина для жалобы")
    }

    //           *****

    fun update(post: Post): Boolean {
        for ((index, oldPost) in posts.withIndex()) {
            if (oldPost.id == post.id) {
                posts[index] = post.copy(
                    comment = post.comment?.copy(),
                    copyright = post.copyright.copy(),
                    like = post.like?.copy(),
                    reposts = post.reposts?.copy(),
                    views = post.views?.copy(),
                    geo = post.geo.copy(),
                    donut = post.donut.copy(),
                    attachments = post.attachments.copyOf()
                )
                return true
            }
        }
        return false
    }

    // для себя
    fun getAllPosts(): String {
        return posts.joinToString("\n")
    }

}


//************************************************************   MAIN   *******************************************************
fun main() {

    val audio = AudioAttachment(Audio())
    val photo = PhotoAttachment(Photo())
    val document = DocumentAttachment(Document())
    val note = NoteAttachment(Note())
    val video = VideoAttachment(Video())

    val attachments = arrayOf(audio, photo, document, note, video)

    val post1 = Post(
        101, 11, 111, 1111, 11111, "1", 111111, 1111111, false, "reply", 11111111,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = true,
        markedAsAds = false,
        isFavourite = false,
        postponedId = 111111111,
        attachments = attachments
    )

    val post2 = Post(
        202, 22, 222, 2222, 22222, "1", 222222, 2222222, false, "reply", 22222222,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = true,
        markedAsAds = false,
        isFavourite = false,
        postponedId = 222222222,
        comment = Comments(30),
        like = Likes(500),
        attachments = attachments
    )

    println(post1)
    println(post2)
    println()

    //  UPDATE

    val postWithUniqueId = Wall()
    postWithUniqueId.add(post1)
    postWithUniqueId.add(post2)
    println(post1)
    println(postWithUniqueId.getAllPosts())


    val post3 = Post(
        1, 33, 333, 3333, 11111, "1", 111111, 1111111, false, "reply", 11111111,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = true,
        markedAsAds = false,
        isFavourite = false,
        postponedId = 111111111,
        attachments = attachments
    )
    println(postWithUniqueId.update(post3))
    println(postWithUniqueId.getAllPosts())

    // CREATE COMMENT   7.1

    println()
    println(postWithUniqueId.createComment(1, comment = Wall.Comment()))

    // CREATE COMPLAINT    7.2
    println()
    println(postWithUniqueId.createComplaint(Wall.Complaint(5, 1, 3)))

}

