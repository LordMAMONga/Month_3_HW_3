package com.geeks.hw_3.ui.on_board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.hw_3.R
import com.geeks.hw_3.data.model.OnBoardModel
import com.geeks.hw_3.databinding.FragmentOnBoardBinding
import com.geeks.hw_3.ui.on_board.adapter.OnBoardAdapter
import me.relex.circleindicator.CircleIndicator3

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter
    private lateinit var indicator: CircleIndicator3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OnBoardAdapter(getOnBordList(), ::navigateToMain, ::onSkip)
        binding.vpOnBoard.adapter = adapter
        indicator = binding.indicator
        indicator.setViewPager(binding.vpOnBoard)

        binding.vpOnBoard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val LastPage = position == adapter.itemCount - 1
                if (LastPage) {
                    binding.btnNext.visibility= View.VISIBLE
                } else {
                    binding.btnNext.visibility= View.INVISIBLE
                }
            }
        })
        binding.btnNext.setOnClickListener {
            navigateToMain()
        }

    }

    fun onSkip(endPosition: Int) {
        binding.vpOnBoard.currentItem = endPosition
    }

    fun navigateToMain() {
        findNavController().navigate(
            OnBoardFragmentDirections.actionOnBoardFragmentToMainFragment()
        )
    }

    private fun getOnBordList() = listOf(
        OnBoardModel(
            "Удобство",
            "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
            R.raw.animation_1
        ), OnBoardModel(
            "Организация",
            "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
            R.raw.animation_2
        ), OnBoardModel(
            "Синхронизация",
            "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
            R.raw.animation_3
        )
    )
}