package com.example.juegosapp.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.juegosapp.R
import com.example.juegosapp.main.model.UserGame

class DatosListAdapter(private var datos: List<UserGame>) :
    RecyclerView.Adapter<DatosListAdapter.DatosViewHolder>() {

    /*En esta sección, se declara la clase DatosListAdapter, que es un adaptador personalizado para el RecyclerView. La clase tiene una variable deleteButtonClickListener, que es una función de orden superior que toma una cadena como parámetro y no devuelve nada. Esta variable se utilizará para asignar un listener al botón de eliminación en cada elemento del RecyclerView.
    La función setDeleteButtonClickListener se utiliza para establecer el listener del botón de eliminación. Recibe como parámetro una función que se ejecutará cuando se haga clic en el botón de eliminación. Esta función se asigna a la variable deleteButtonClickListener.
    Espero que estos comentarios aclaren el propósito de estas líneas de código.*/
    // Declaración de la variable para el listener del botón de eliminación
    private var deleteButtonClickListener: ((String) -> Unit)? = null

   /* En Kotlin, Unit es un tipo de dato que representa la ausencia de un valor significativo,
    es similar al tipo void en otros lenguajes de programación. Se utiliza
    cuando una función no devuelve ningún valor explícito.
    En el contexto de ((String) -> Unit)? = null, ((String) -> Unit) es el
    tipo de dato de la variable deleteButtonClickListener. Es una función que toma una
    cadena como parámetro (String) y no devuelve ningún valor significativo (Unit). El signo
    de interrogación (?) al final indica que la variable puede ser nula, es decir, puede que
    no tenga asignado ningún valor.
    En resumen, la variable deleteButtonClickListener es una función opcional
    que se ejecutará cuando se haga clic en el botón de eliminación en cada elemento
    del RecyclerView. Si no se asigna ningún listener, su valor será nulo (null).*/
    // Función para establecer el listener del botón de eliminación
    fun setDeleteButtonClickListener(listener: (String) -> Unit) {
        deleteButtonClickListener = listener
    }

    // Crea una nueva vista para cada elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        // Infla el layout del elemento de lista desde el archivo de diseño 'article_item.xml'
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)

        return DatosViewHolder(view)
    }

    // Vincula los datos del elemento en la posición dada con la vista
    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        // Obtiene los datos actuales en la posición dada
        val currentDatos = datos[position]
        // Vincula los datos con la vista del titular de los datos
        holder.bind(currentDatos)
    }

    // Devuelve la cantidad total de elementos en la lista de datos
    override fun getItemCount(): Int = datos.size

    // Define la clase ViewHolder que representa cada elemento de la lista
    inner class DatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var idEliminar: String = ""
        private val nombreTextView: TextView = view.findViewById(R.id.username)
        private val precioTextView: TextView = view.findViewById(R.id.realname)
        private val cantidadTextView: TextView = view.findViewById(R.id.age)
        private val idEliminarTextView: TextView = view.findViewById(R.id.idEliminar)
        private val deleteButton: Button = view.findViewById(R.id.deleteButton)

        fun bind(datos: UserGame) {
            // Establecer los valores de los campos de texto con los datos correspondientes
            nombreTextView.text = datos.gameName
            precioTextView.text = datos.realName
            cantidadTextView.text = datos.age.toString()
            idEliminar = datos.id.toString()

            deleteButton.setOnClickListener {
                // Aquí puedes llamar a la función deleteButtonClickListener y pasar el valor de idEliminar
                deleteButtonClickListener?.invoke(idEliminar)
            }
        }
    }
}
