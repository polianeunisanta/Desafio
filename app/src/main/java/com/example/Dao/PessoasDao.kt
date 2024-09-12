package com.example.myapplication.Dao

import com.example.myapplication.Model.Filme

class PessoasDao {
    companion object {
        private val filmes = mutableListOf<Filme>()
    }

    fun cadastraFilme(filme: Filme) {
        Companion.filmes.add(filme)
    }

    fun obterLista(): List<Filme> {
        return Companion.filmes
    }
}