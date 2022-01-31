package com.tecnica.prueba.ui.character.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.tecnica.prueba.R
import com.tecnica.prueba.databinding.FragmentHeroeDetailBinding
import com.tecnica.prueba.ui.extension.load
import com.tecnica.prueba.ui.BaseFragment
import com.tecnica.viewmodel.character.HeroeDetailViewModel
import com.tecnica.viewmodel.manager.ResponseState
import com.tecnica.viewmodel.character.model.CharacterView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroeDetailFragment: BaseFragment() {
    private var _binding: FragmentHeroeDetailBinding? = null
    private val binding get() = _binding!!

    private val heroeDetailViewModel: HeroeDetailViewModel by viewModel()
    private val args: HeroeDetailFragmentArgs by navArgs<HeroeDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroeDetailBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val thumbnail = binding.imThumbnailCharacter
        activity?.startPostponedEnterTransition()
        with(thumbnail) {
            transitionName = "thumbnailCharacter_${args.characterId}"
            load(args.url) {
                activity?.supportStartPostponedEnterTransition()
            }
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heroeDetailViewModel.getCharacterId(args.characterId)
        heroeDetailViewModel.liveData.observe(this, {
            when (it) {
                ResponseState.Loading -> {
                    showLoading()
                }
                is ResponseState.Error -> {
                    errorManager.catchException(it.exceptiontype, this)
                    hideLoading()
                }
                is ResponseState.Success -> {
                    updateDataScreen(it.item)
                    hideLoading()
                }
            }
        })
    }

    fun updateDataScreen(characterModel: CharacterView) {
        binding.tvId.text = characterModel.id.toString()
        binding.tvNameCharacter.text = characterModel.name

        if (characterModel.description.isNotBlank()) {
            binding.tvDescription.text = characterModel.description
        } else {
            binding.tvDescription.text = context?.resources?.getString(R.string.fragment_detail_dont_description)
        }
    }



}