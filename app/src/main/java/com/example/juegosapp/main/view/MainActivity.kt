package com.example.juegosapp.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.juegosapp.R
import com.example.juegosapp.databinding.ActivityMainBinding
import com.example.juegosapp.main.model.UserGame
import com.example.juegosapp.main.model.UserGameRoomDatabase
import com.example.juegosapp.main.model.repository.UserGameRepository
import com.example.juegosapp.main.view.viewmodel.UserGameViewModel
import com.example.juegosapp.main.view.viewmodel.UserGameViewModelFactory
import androidx.fragment.app.add
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(),BlankFragment.ListaButtonClickListener  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userGameViewModel: UserGameViewModel
    internal lateinit var data: List<UserGame>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
            val boton = findViewById<Button>(R.id.deleteButton)
        val id = findViewById<TextView>(R.id.idEliminar)
        val recicler  = findViewById<RecyclerView>(R.id.recyclerView)


        //instanciar la bd
        val database = UserGameRoomDatabase.getDatabase(applicationContext)
        val usergameDao = database.userGameDao()
        val frasesRepository = UserGameRepository(usergameDao)
        val frasesViewModelFactory = UserGameViewModelFactory(frasesRepository)
        userGameViewModel = ViewModelProvider(this, frasesViewModelFactory).get(UserGameViewModel::class.java)

        userGameViewModel.allDatos.observe(this, Observer { datosList ->
            // Aquí cargamos la variable global data con lta lista de allDatos
            this.data = datosList
        })
       // var user = UserGame(null, "vercer", "Fernando COntreras", 37)
       // userGameViewModel.insert(user)
        // userGameViewModel.deleteAll()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BlankFragment>(R.id.fragmentContainer)
        }
    }
    override fun onListaButtonClick() {
        try {
            // Verificar si la propiedad ids está inicializada
            if (!data.isEmpty()) {
                // Acceder a la propiedad ids
                val listaFragment = ListaFragment()
                val bundle = Bundle()
                //   bundle.putString("total", total.toString())
                listaFragment.arguments = bundle
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainer,listaFragment)
                    addToBackStack(null)
                }
            } else {
                // La propiedad ids no ha sido inicializada
                // Realiza la lógica correspondiente en este caso
                Toast.makeText(this, "la lista está vacía", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Manejar la excepción en caso de que ocurra algún error
            Toast.makeText(this, "Error al acceder a los datos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
     fun eliminar(){
         userGameViewModel.deleteAll()
         data = emptyList()
         val listaFragment = ListaFragment()
         supportFragmentManager.beginTransaction()
             .replace(R.id.fragmentContainer, listaFragment)
             .commit()
     }

    // Función para el botón insertar
    override fun insertar() {



        try {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val nameEditText = findViewById<EditText>(R.id.name)
            val surnameEditText = findViewById<EditText>(R.id.surname)
            val ageEditText = findViewById<EditText>(R.id.age)
            username.trim().replaceFirstChar { it.uppercase() }.replace("\\s".toRegex(), "")
            val nombre = nameEditText.text.toString().trim().replaceFirstChar { it.uppercase() }.replace("\\s".toRegex(), "")
            val apellido = surnameEditText.text.toString().trim().replaceFirstChar { it.uppercase() }.replace("\\s".toRegex(), "")
            val age: Int? = ageEditText.text.toString().trim().toIntOrNull()
            if (username.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || age == null) {
                // Al menos uno de los campos está vacío
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Todos los campos están completos
                var name = "$nombre $apellido"

                val datos = UserGame(null, username, name, age)
                userGameViewModel.insert(datos)
                Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Manejar la excepción en caso de que ocurra algún error
            Toast.makeText(this, "Error al agregar a los datos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    fun eliminarUno(idEliminar: String) {
        val id = idEliminar.toIntOrNull()
        if (id != null) {
            userGameViewModel.eliminarUno(id)
            data = data.filter { it.id != id } // Actualiza la lista 'data' eliminando el elemento correspondiente al ID
            val listaFragment = ListaFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, listaFragment)
                .commit()
        } else {
            // La cadena no es un valor numérico válido
            // Maneja el error según corresponda
            Toast.makeText(this, "ID no válido", Toast.LENGTH_SHORT).show()
        }
    }



}


