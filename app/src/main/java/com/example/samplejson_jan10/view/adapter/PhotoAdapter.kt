package com.example.samplejson_jan10.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.samplejson_jan10.databinding.FragmentTodoBinding
import com.example.samplejson_jan10.databinding.RowItemPhotoBinding
import com.example.samplejson_jan10.model.network.models.Photo


class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private val photosList = mutableListOf<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            RowItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photosList[position])
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    fun submitList(photos: List<Photo>) {
        photosList.clear()
        photosList.addAll(photos)
        notifyDataSetChanged()
    }

    class PhotoViewHolder(
        private val binding: RowItemPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) = with(binding) {
            tvAlbumId.text = photo.albumId.toString()
            tvId.text = photo.id.toString()
            tvTitle.text = photo.title
            tvUrl.text = photo.url
            tvThumbnail.text = photo.thumbnailUrl

//            ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
//
//            Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);

            Glide.with(ivUrl.context).load(photo.url).into(ivUrl)
            Glide.with(ivThumbnail.context).load(photo.thumbnailUrl).into(ivThumbnail)

            // implement url and thumbnail with glide later

        }
    }
}