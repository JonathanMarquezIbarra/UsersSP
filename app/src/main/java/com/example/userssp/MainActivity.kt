package com.example.userssp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)

        val isfirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("sp", "${getString(R.string.sp_first_time)} = $isfirstTime")

        if(isfirstTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)

            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.etUsername)
                        .text.toString()
                    with(preferences.edit()){
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username)
                            .apply()
                    }
                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT)
                        .show()
                }
                .show()
        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }


        userAdapter = UserAdapter(getUsers(),this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers():MutableList<User>{
        val users = mutableListOf<User>()

        val rodrigo = User(1, "Rodrigo", "Ibarra", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.-TG3lNGKl18gxQ_HIY4ttwAAAA%26pid%3DApi&f=1")
        val romina = User(2, "Romina", "Tavares", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Funiversityinnovation.org%2Fimages%2Fthumb%2F3%2F34%2FRomi_Dominzain_UIF.jpg%2F300px-Romi_Dominzain_UIF.jpg&f=1&nofb=1")
        val enrique = User(3, "Enrique", "Vallesteros", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flive.staticflickr.com%2F5605%2F31126944790_2624c8d834_b.jpg&f=1&nofb=1")
        val sandra = User(4,"Sandra","Benitez","https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimages.pexels.com%2Fphotos%2F920141%2Fpexels-photo-920141.jpeg%3Fauto%3Dcompress%26cs%3Dtinysrgb%26h%3D750%26w%3D1260&f=1&nofb=1")

        users.add(rodrigo)
        users.add(romina)
        users.add(enrique)
        users.add(sandra)
        users.add(rodrigo)
        users.add(romina)
        users.add(enrique)
        users.add(sandra)
        users.add(rodrigo)
        users.add(romina)
        users.add(enrique)
        users.add(sandra)
        users.add(rodrigo)
        users.add(romina)
        users.add(enrique)
        users.add(sandra)

        return users
    }

    override fun onClick(user: User, position:Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}" ,Toast.LENGTH_SHORT).show()
    }
}