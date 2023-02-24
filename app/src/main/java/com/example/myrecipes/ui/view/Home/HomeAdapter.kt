package com.example.myrecipes.ui.view.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myrecipes.R
import com.example.myrecipes.databinding.ItemRecipeBinding
import com.example.myrecipes.domain.model.Recipe

class HomeAdapter(
    private val context: Context,
    private val recipes: List<Recipe>,
    private val onClickListener: (recipe: Recipe) -> Unit
) : RecyclerView.Adapter<HomeAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.read(recipes[position], onClickListener)
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mBinding = ItemRecipeBinding.bind(view)

        fun read(recipe: Recipe, onClickListener: (recipe: Recipe) -> Unit) {
            Glide
                .with(context)
                .load(recipe.imagenes[0])
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .placeholder(R.drawable.sin_foto)
                .error(R.drawable.sin_foto)
                .into(mBinding.ivPhoto)
            mBinding.tvName.text = recipe.nombre
            mBinding.tvDescription.text = recipe.descripcion
            mBinding.tvDuration.text = recipe.duracion
            mBinding.tvDifficulty.text = recipe.dificultad
            mBinding.cardRecipe.setOnClickListener { onClickListener(recipe) }
        }

    }

}