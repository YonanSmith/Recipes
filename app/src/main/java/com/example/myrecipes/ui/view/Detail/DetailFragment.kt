package com.example.myrecipes.ui.view.Home.Detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentDetailBinding
import com.example.myrecipes.domain.model.Recipe
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class DetailFragment : Fragment() {

    private var _mBinding: FragmentDetailBinding? = null
    private val mBinding get() = _mBinding!!

    private lateinit var recipe: Recipe
    private val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            recipe = it.getParcelable("dataRecipe")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        loadImages()
        loadData()

        mBinding.ivMap.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("dataRecipe", recipe.autor)
            findNavController().navigate(R.id.mapFragment, bundle)
        }
    }

    private fun loadData() {
        mBinding.tvName.text = recipe.nombre.uppercase()
        mBinding.tvDescription.text = recipe.descripcion
        var ingredientes = ""
        recipe.ingredientes.map {
            ingredientes += "∘ $it \n"
        }
        mBinding.tvDuration.text = recipe.duracion
        mBinding.tvDifficulty.text = recipe.dificultad
        mBinding.tvIngredients.text = ingredientes
        mBinding.tvDataPreparation.text = recipe.preparacion
        var dataAutor = ""
        recipe.autor.apply {
            dataAutor = "Nombres: $nombre\n" +
                    "Edad: $edad\n" +
                    "Nacionalidad: $nacionalidad"
        }
        mBinding.tvDataAutor.text = dataAutor
    }

    private fun loadImages() {
        list.clear()
        recipe.imagenes.map {
            list.add(CarouselItem(it))
        }
        mBinding.containerCarousel.apply {
            addData(list)
            registerLifecycle(viewLifecycleOwner)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }
}