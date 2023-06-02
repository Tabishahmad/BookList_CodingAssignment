//package com.example.bookapi.presentation.favourite
//
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.activityViewModels
//import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.bookapi.R
//import com.example.bookapi.common.LIST_INDEX
//import com.example.bookapi.common.hide
//import com.example.bookapi.common.show
//import com.example.bookapi.common.showCustomToast
//import com.example.bookapi.databinding.FragmentBookFavouriteBinding
//import com.example.bookapi.domain.model.Book
//import com.example.bookapi.presentation.core.base.BaseFragment
//import com.example.bookapi.presentation.list.BookListViewModel
//import com.example.bookapi.presentation.list.ImageListAdapter
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//
//@AndroidEntryPoint
//class FavouriteBooksFragment : BaseFragment<BookListViewModel,FragmentBookFavouriteBinding>(
//    R.layout.fragment_book_favourite
//),ImageListAdapter.ItemClickListener {
//    override val viewModel: BookListViewModel by activityViewModels()
//
//    override fun init() {
//        viewModel.fetchList()
//    }
//
//    private fun hideProgressBar(){
//        binding.progressBar.hide()
//    }
//
//    private fun setupRecyclersView(list:List<Book>){
//        hideProgressBar()
//        if(list.isEmpty()){
//            binding.rv.hide()
//            binding.nodata.show()
//        }else {
//            binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
//            binding.rv.setData(list)
//            binding.rv.setItemClickListener(this)
//        }
//    }
//
//    override fun onItemClick(view: View, any: Any, index: Int) {
//        requireContext().getString(R.string.not_implemented).showCustomToast(requireContext(),Toast.LENGTH_LONG)
//    }
//
//
//    override fun observeViewModel() {
//        lifecycleScope.launch {
//            viewModel.getAllFavouriteBooks().collect{
//                setupRecyclersView(it)
//            }
//        }
//    }
//}