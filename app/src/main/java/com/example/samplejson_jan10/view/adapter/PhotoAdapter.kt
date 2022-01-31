package com.example.samplejson_jan10.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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


//            val urlTestString = "https://static.vecteezy.com/system/resources/previews/000/450/367/original/android-vector-icon.jpg"
//            val urlTestString2 = "https://via.placeholder.com/600/92c952"
//            val urlTestString3 = "https://purepng.com/public/uploads/large/51500927739gw8errqafm7fj1ftscuml1qd1msydpvtrrdhxct8a89hkc0rkarovqknvmrye9i08o5zoop1tgh71bslw4bio58a7rxz2tlkntvr.png"
//            val urlTestString4 = "https://via.placeholder.com/600/92c952.png"

            Glide.with(ivUrl.context).load(photo.url + ".png").into(ivUrl)
            Glide.with(ivThumbnail.context).load(photo.thumbnailUrl + ".png").into(ivThumbnail)
            //note that the url passed from the api is not working but this urlTestString is
//            Glide.with(ivUrl.context).load(urlTestString).into(ivUrl)
//            Glide.with(ivUrl.context).load(urlTestString).into(ivThumbnail)
//            ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
//
//            Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);

//              AppGlideModule
            //MyAppGlideModule.with(ivUrl.context).load(photo.url).into(ivUrl)
//            Glide.with(ivUrl.context).load(photo.url).into(ivUrl)
//            Glide.with(ivThumbnail.context).load(photo.thumbnailUrl).into(ivThumbnail)

            // implement url and thumbnail with glide later

        }
    }
}