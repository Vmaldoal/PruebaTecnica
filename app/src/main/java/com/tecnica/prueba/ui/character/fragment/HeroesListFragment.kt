package com.tecnica.prueba.ui.character.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.tecnica.prueba.databinding.FragmentHeroesListBinding
import com.tecnica.prueba.ui.character.adapter.CharactersListAdapter
import com.tecnica.prueba.ui.BaseFragment
import com.tecnica.viewmodel.character.model.CharacterView
import com.tecnica.viewmodel.character.HeroesListViewModel
import com.tecnica.viewmodel.manager.ResponseState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class HeroesListFragment : BaseFragment(), CharactersListAdapter.ICharactersListAdapter {
    private var _binding: FragmentHeroesListBinding? = null
    private val binding get() = _binding!!

    private val heroesListViewModel: HeroesListViewModel by viewModel()
    private lateinit var charactersListAdapter: CharactersListAdapter

    private var page: Int = 0

    private var savedViewInstande: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedViewInstande != null) {
            savedViewInstande
        } else {
            _binding = FragmentHeroesListBinding.inflate(inflater, container, false)
            savedViewInstande = binding.root
            loadCharacters(page)
        }

        return savedViewInstande
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heroesListViewModel.liveData.observe(this, { listCharacters ->
            when (listCharacters) {
                ResponseState.Loading -> {
                    showLoading()
                }
                is ResponseState.Error -> {
                    errorManager.catchException(listCharacters.exceptiontype, this)
                    hideLoading()
                }
                is ResponseState.Success -> {
                    updateAdapterListView(listCharacters.item)
                    hideLoading()
                }
            }
        })
    }

    override fun loadMore() {
        loadCharacters(page)
    }

    override fun clickItem(id: Long, imageView: ImageView, url: String) {
        val action =
            HeroesListFragmentDirections.actionHeroesListFragmentToHeroeDetailFragment(
                id,
                url
            )
        val extras = FragmentNavigatorExtras(imageView to "thumbnailCharacter_$id")
        super.navigator(action, extras)
    }

    private fun loadCharacters(page: Int) {
        heroesListViewModel.getListCharacters(page)
        this.page++
    }

    private fun updateAdapterListView(list: ArrayList<CharacterView>) {
        if (binding.rvHeroesList.adapter != null) {
            addMoreItemsList(list)
        } else {
            createAdapterListView(list)
        }
    }

    private fun createAdapterListView(list: ArrayList<CharacterView>) {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rvHeroesList.layoutManager = linearLayoutManager
        charactersListAdapter = CharactersListAdapter(list, this)
        binding.rvHeroesList.adapter = charactersListAdapter
    }

    private fun addMoreItemsList(list: List<CharacterView>) {
        if (list.isNotEmpty()) {
            charactersListAdapter.addMoreItems(list)
        } else {
            charactersListAdapter.finishList()
        }
    }
}