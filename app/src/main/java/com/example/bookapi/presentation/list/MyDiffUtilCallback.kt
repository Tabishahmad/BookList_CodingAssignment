//package com.example.bookapi.presentation.list
//
//import androidx.recyclerview.widget.DiffUtil
//import com.example.bookapi.domain.model.Book
//
//class MyDiffUtilCallback(
//    private val oldList: List<Book>,
//    private val newList: List<Book>
//) : DiffUtil.Callback() {
//
//    override fun getOldListSize(): Int {
//        return oldList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return newList.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        // Compare unique identifiers of the items
//        return oldList[oldItemPosition].bookHashId == newList[newItemPosition].bookHashId &&
//                oldList[oldItemPosition].bookTitle == newList[newItemPosition].bookTitle &&
//                oldList[oldItemPosition].thumbnailUrl == newList[newItemPosition].thumbnailUrl
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        // Compare the contents of the items
//        return oldList[oldItemPosition] == newList[newItemPosition]
//    }
//}
