package com.example.juegosapp.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.juegosapp.R
import android.annotation.SuppressLint
import android.widget.Button


@Suppress("UNREACHABLE_CODE")
class BlankFragment : Fragment() {
    //interfaces
    interface ListaButtonClickListener {
        fun onListaButtonClick()
        fun insertar()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        view?.findViewById<Button>(R.id.btnLista)?.setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? ListaButtonClickListener
            // Llama al método onCarroButtonClick de la interfaz si está implementado en la actividad
            listener?.onListaButtonClick()
        }

        // Configura la acción del botón "Agregar"
        view?.findViewById<Button>(R.id.btnAgregar)?.setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? ListaButtonClickListener
            // Llama al método insertar de la interfaz si está implementado en la actividad
            listener?.insertar()
        }
        return view
    }

}