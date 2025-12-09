package com.geeks.hw_3.ui.on_board.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeks.hw_3.data.model.OnBoardModel
import com.geeks.hw_3.databinding.ItemOnBoardBinding

class OnBoardAdapter(
    val listOnBoard: List<OnBoardModel>,
    val onStart: () -> Unit,
    val onSkip: (Int) -> Unit
) :
    RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: OnBoardViewHolder,
        position: Int
    ) {
        holder.onBind(listOnBoard[position])
    }

    override fun getItemCount() = listOnBoard.size

    inner class OnBoardViewHolder(
        private val binding: ItemOnBoardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: OnBoardModel) {
            binding.apply {
                title.text = model.title
                detail.text = model.desc
                lottie.setAnimation(model.animationId)

                btnNext.setOnClickListener {
                    onStart()
                }

                skip.setOnClickListener {
                    onSkip(listOnBoard.size)
                }

                if(adapterPosition==listOnBoard.size-1){
                    skip.visibility = View.INVISIBLE
                }else{
                    btnNext.visibility = View.INVISIBLE
                }
            }
        }

    }
}


