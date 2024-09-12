package com.example.myapplication.View

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.Dao.PessoasDao
import com.example.myapplication.Model.Filme
import com.example.desafio.R

class MainActivity3 : AppCompatActivity(R.layout.activity_main3) {
    private val dao = PessoasDao()
    private lateinit var edtVideo: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.main)
        val btnCadastra = findViewById<Button>(R.id.btnCadastra)
        val edtNome = findViewById<EditText>(R.id.txtNome)
        val edtDesc = findViewById<EditText>(R.id.txtNumero)
        val edtNota = findViewById<EditText>(R.id.txtImagem)
        edtVideo = findViewById(R.id.txtVideo)
        val semconta = findViewById<TextView>(R.id.sem_conta)



        btnCadastra.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val desc = edtDesc.text.toString().trim()
            val nota = edtNota.text.toString().trim()
            val filme = edtVideo.text.toString().trim()

            if (nome.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha o campo Nome.", Toast.LENGTH_SHORT).show()
            }else if(desc.isEmpty()){
                Toast.makeText(this, "Por favor, preencha o campo Descrição.", Toast.LENGTH_SHORT).show()

            }else if(nota.isEmpty()){
                Toast.makeText(this, "Por favor, preencha o campo Nota.", Toast.LENGTH_SHORT).show()

            }else if(filme.isEmpty()){
                Toast.makeText(this, "Por favor, preencha o campo Url do filme.", Toast.LENGTH_SHORT).show()

            }
            else {
                edtNome.clearFocus()
                edtDesc.clearFocus()
                edtNota.clearFocus()
                edtVideo.clearFocus()

                val filme = Filme(nome, desc, nota, filme)

                dao.cadastraFilme(filme)

                Toast.makeText(this, "Filme cadastrado com sucesso", Toast.LENGTH_SHORT).show()

                // Limpar os campos apenas se o cadastro for bem-sucedido
                edtNome.text.clear()
                edtDesc.text.clear()
                edtNota.text.clear()
                edtVideo.text.clear()
            }
        }


        semconta.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        constraintLayout.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val v = currentFocus
                if (v != null) {
                    val rect = Rect()
                    v.getGlobalVisibleRect(rect)
                    if (!rect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        v.clearFocus()
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.windowToken, 0)
                    }
                }
            }
            true
        }


    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}
