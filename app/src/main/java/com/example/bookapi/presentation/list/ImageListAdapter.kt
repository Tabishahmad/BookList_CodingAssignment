//package com.example.bookapi.presentation.list
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.bookapi.common.hide
//import com.example.bookapi.common.loadImageWithGlide
//import com.example.bookapi.common.show
//import com.example.bookapi.databinding.ImageRowBinding
//import com.example.bookapi.domain.model.Book
//
//class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageHolder>() {
//
//    private var layoutInflater: LayoutInflater? = null
//    private var imageClickListener: ItemClickListener? = null
//    private var items: List<Book> = emptyList()
//
//    class ImageHolder(val b: ImageRowBinding) : RecyclerView.ViewHolder(b.root) {
//       fun setImage(thumbnailURL:String) {
//            b.iv.loadImageWithGlide(b.iv.context, thumbnailURL)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
//        if (layoutInflater == null) {
//            layoutInflater = LayoutInflater.from(parent.context)
//        }
//        val b = ImageRowBinding.inflate(layoutInflater!!, parent, false)
//        return ImageHolder(b)
//    }
//
//    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
//        val book = items.get(position)
//        holder.setImage(book.thumbnailUrl)
//        holder.b.card.setOnClickListener {
//            imageClickListener?.onItemClick(it, book, position)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//
//    fun setClickListener(listener: ItemClickListener) {
//        this.imageClickListener = listener
//    }
//
//    fun interface ItemClickListener {
//        fun onItemClick(view: View, any: Any, index: Int)
//    }
//    fun updateList(newList: List<Book>) {
//        val diffCallback = MyDiffUtilCallback(items, newList)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        items = newList
//        diffResult.dispatchUpdatesTo(this)
//    }
//}