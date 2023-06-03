package com.example.bookapi.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookapi.common.DATABASE_TABLE_NAME
import kotlinx.parcelize.Parcelize

/**
 * data class along with Room DB
 * Entity
 */
@Entity(tableName = DATABASE_TABLE_NAME)
@Parcelize
data class Book(@PrimaryKey
                val bookHashId:String,
                val bookTitle:String,
                val thumbnailUrl:String,
                var isFav:Boolean = false): Parcelable