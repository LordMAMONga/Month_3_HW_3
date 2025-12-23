package com.geeks.hw_3.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.geeks.hw_3.R
import com.geeks.hw_3.databinding.FragmentMainBinding
import com.geeks.hw_3.ui.App
import com.geeks.hw_3.ui.main.adapter.NotesAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.oAuthCredential

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter = NotesAdapter()
    private var isGrid = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotes.adapter = adapter
        adapter.addNotes(App.database.dao().getAllNotes())

        val user = FirebaseAuth.getInstance().currentUser
        binding.nick.text = user?.email ?: "Нет почты "

        Glide.with(requireContext())
            .load(user?.photoUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(binding.icAvatarAccount)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.addNotes)
        }

        binding.btnExit.setOnClickListener {
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Выход")
                .setMessage("Вы точно хотите выйти из аккаунта?")
                .setPositiveButton("Да") { _, _ ->
                    com.google.firebase.auth.FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(
                        R.id.action_mainFragment_to_authFragment,
                        null,
                        androidx.navigation.NavOptions.Builder().setPopUpTo(R.id.nav_graph, true)
                            .build()
                    )
                }
                .setNegativeButton("Отмена", null).show()
        }
        binding.icCharge.setOnClickListener {
            isGrid = !isGrid

            if (isGrid){

                binding.rvNotes.layoutManager = androidx.recyclerview.widget.GridLayoutManager(requireContext(),2)
            }else{
                binding.rvNotes.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            }
        }

    }


}

