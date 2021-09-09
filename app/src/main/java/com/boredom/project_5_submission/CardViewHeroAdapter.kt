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
    private var onItemClickCallback: OnItemClickCallback? = null
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

            //  Toast.makeText(holder.itemView.getContext(), "Detail " + listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            //  onItemClickCallback.onItemClicked(listHero.get(holder.getAdapterPosition()));
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
    }

}