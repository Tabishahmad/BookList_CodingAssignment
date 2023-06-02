//package com.example.bookapi.presentation.detail
//
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.lifecycleScope
//import com.example.bookapi.R
//import com.example.bookapi.common.LIST_INDEX
//import com.example.bookapi.common.hide
//import com.example.bookapi.common.show
//import com.example.bookapi.databinding.FragmentBookDetailBinding
//import com.example.bookapi.domain.model.Book
//import com.example.bookapi.presentation.core.base.BaseFragment
//import com.example.bookapi.presentation.core.ViewState
//import com.example.bookapi.presentation.list.BookListViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//@AndroidEntryPoint
//class BookDetailFragment : BaseFragment<BookListViewModel, FragmentBookDetailBinding>(
//    R.layout.fragment_book_detail
//) {
//    override val viewModel: BookListViewModel by viewModels()
//    private var currentIndex: Int = 0
//
//    override fun init() {
//        arguments?.getInt(LIST_INDEX)?.let {
//            currentIndex = it
//            viewModel.fetchList()
//        }
//    }
//    private fun hideProgressBar(){
//        binding.progressBar.hide()
//    }
//    private fun showProgressBar(){
//        binding.progressBar.show()
//    }
//    override fun observeViewModel() {
//        performCoroutineTask {
//            viewModel.getviewStateFlow().collect { viewState ->
//                when (viewState) {
//                    is ViewState.Success -> {
//                        hideProgressBar()
//                        println("handleFav ViewState.Success")
//                        handleResponseSuccess(viewState.result.get(currentIndex))
//                    }
//                    is ViewState.Failure -> {
//                        hideProgressBar()
//                        println("handleFav Failure")
//                    }
//                    else -> {}
//                }
//            }
//        }
//    }
//
//    private fun handleResponseSuccess(book: Book) {
//        binding.book = book
//        setFavoriteBookResource(book)
//        setText(book.thumbnailUrl)
//    }
//
//    private fun setText(string: String) {
//        binding.textView.setText(string)
//    }
//
//    private fun setFavoriteBookResource(book: Book) {
//        viewModel.isFavoriteBook(book) { boolean ->
//            println("handleFav " + boolean)
//            val imageRes = if (boolean) R.drawable.ic_favorite else R.drawable.ic_favorite_border
//            binding.favoriteButton.setImageResource(imageRes)
//        }
//    }
//
//}