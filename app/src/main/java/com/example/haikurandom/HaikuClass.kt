package com.example.haikurandom

import com.google.firebase.firestore.DocumentId
import java.util.*

data class HaikuClass(
    @DocumentId

    val id : String = "",
    val kami : String = "",
    val naka : String = "",
    val shimo : String = "",
    var createdAt: Date = Date(System.currentTimeMillis()),
)
