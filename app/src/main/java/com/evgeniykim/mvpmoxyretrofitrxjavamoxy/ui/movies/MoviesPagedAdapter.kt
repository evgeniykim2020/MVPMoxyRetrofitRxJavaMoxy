package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.R
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.databinding.ItemViewBinding
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.utils.ImageLoadUtils

class MoviesPagedAdapter(val onMovieClickListener: OnMovieClickListener) : PagedListAdapter<Movie, MoviesViewHolder>(
    DiffUtilCallBack()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//
//        return MoviesViewHolder(
//            inflater.inflate(
//                R.layout.item_view,
//                parent, false
//            )
//        )
        val inflateBinding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(inflateBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.itemView.setOnClickListener {
            onMovieClickListener.onMovieClick(getItem(position)!!)
        }
    }
}

//class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    fun bind(movie: Movie) {
//        itemView.textViewTitle.text = movie.title
//    }
//
//}

class MoviesViewHolder(var binding: ItemViewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.textViewTitle.text = movie.title
        binding.textViewDescription.text = movie.overview
        ImageLoadUtils.loadImage(movie.posterPath!!, binding.imageViewCover)
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}


interface OnMovieClickListener {
    fun onMovieClick(movie: Movie)
}