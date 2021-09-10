package com.boredom.project_5_submission

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewHeroAdapter(private val listHero: ArrayList<DotaHero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewHolder>() {
    //Declaration
    private var onItemClickCallback: OnItemClickCallback? = null

    private var isFavorite: Boolean = false

    interface OnItemClickCallback{
        fun onItemClicked(data : DotaHero)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHeroAdapter.CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_hero, parent, false)
        return CardViewHolder(view)
    }


    override fun onBindViewHolder(holder: CardViewHeroAdapter.CardViewHolder, position: Int) {
        val hero = listHero[position]

        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail
        holder.btnDetail.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("img_hero", hero.photo)
            intentDetail.putExtra("nama_hero", hero.name)
            intentDetail.putExtra("info_hero", hero.detail)
            holder.itemView.context.startActivity(intentDetail)
//            Toast.makeText(holder.itemView.context, "Detail " + listHero.get(holder.adapterPosition).name, Toast.LENGTH_SHORT).show();
//            onItemClickCallback?.onItemClicked(listHero.get(holder.adapterPosition));
        }

        holder.btnFavorite.setOnClickListener{
            isFavorite = if(!isFavorite){
                holder.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(holder.itemView.context, "Kamu memasukkan " + listHero[holder.adapterPosition].name + " sebagai hero favorite", Toast.LENGTH_SHORT).show()
                true

            }else{
                holder.btnFavorite.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                false
            }
        }
    }

    override fun getItemCount(): Int {
       return listHero.size
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var btnDetail: Button = itemView.findViewById(R.id.btn_detail)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_favorite)
    }


}