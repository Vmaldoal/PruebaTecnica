package com.tecnica.prueba.ui.character.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tecnica.prueba.R
import com.tecnica.viewmodel.character.model.CharacterView

class CharactersListAdapter(
    private val listCharacterModels: ArrayList<CharacterView>,
    private val listener: ICharactersListAdapter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_LIST = 0
    private val TYPE_FOOTER = 1

    private var stateList: StateList = StateList.LOADING

    enum class StateList {
        LOADING,
        LOADED,
        FINISH
    }

    fun addMoreItems(moreCharacterModels: List<CharacterView>){
        val actualListCharacter = listCharacterModels.size
        listCharacterModels.addAll(moreCharacterModels)
        notifyItemInserted(actualListCharacter + 1)
    }

    fun finishList() {
        stateList = StateList.FINISH
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_FOOTER -> {
                val inflater = LayoutInflater.from(parent.context)
                FooterViewHolder(inflater, parent)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                CharacterViewHolder(inflater, parent)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == listCharacterModels.size) {
            TYPE_FOOTER
        } else {
            TYPE_LIST
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_LIST) {
            holder as CharacterViewHolder
            val characterModel: CharacterView = listCharacterModels[position]
            holder.bind(characterModel, listener)
        } else {
            holder as FooterViewHolder
            if (stateList != StateList.FINISH) {
                holder.bind(listener)
                stateList = StateList.LOADED
            }
            holder.stateList = stateList
            holder.refreshStateList()
        }
    }

    override fun getItemCount(): Int {
        return listCharacterModels.size + 1
    }



    class CharacterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.row_character_list, parent, false)) {

        private var name: TextView = itemView.findViewById(R.id.tvNameCharacter)
        private var thumbnail: ImageView = itemView.findViewById(R.id.imThumbnailCharacter)
        private var clickItem: ConstraintLayout = itemView.findViewById(R.id.rowCharacterList)

        fun bind(characterModel: CharacterView, listener: ICharactersListAdapter) {

            name.text = characterModel.name

            Glide.with(thumbnail.context).load(characterModel.thumbnail).centerCrop()
                .into(thumbnail)

            thumbnail.transitionName = "thumbnailCharacter_${characterModel.id}"

            clickItem.setOnClickListener {
                listener.clickItem(characterModel.id, thumbnail, characterModel.thumbnail)
            }
        }
    }

    class FooterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.row_footer_list, parent, false)) {

        private var loadMore: TextView = itemView.findViewById(R.id.tvLoadMore)

        var stateList: StateList = StateList.LOADED

        fun bind(listener: ICharactersListAdapter) {
            loadMore.setOnClickListener {
                listener.loadMore()
                stateList = StateList.LOADING
                refreshStateList()
            }
        }

        fun refreshStateList() {
            when (stateList) {
                StateList.LOADED -> {
                    loadMore.text = loadMore.context.resources.getString(R.string.footer_load_more)
                }

                StateList.LOADING -> {
                    loadMore.text = loadMore.context.resources.getString(R.string.footer_loading)
                }

                StateList.FINISH -> {
                    loadMore.visibility = View.GONE
                }
            }
        }
    }

    interface ICharactersListAdapter {
        fun loadMore()
        fun clickItem(id: Long, imageView: ImageView, url: String)
    }
}