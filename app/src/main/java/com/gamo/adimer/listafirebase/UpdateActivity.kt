package com.gamo.adimer.listafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UpdateActivity : AppCompatActivity() {
    private val TAG: String?="MYTAG"
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val first=findViewById<EditText>(R.id.firstUpdate)
        val last=findViewById<EditText>(R.id.lastUpdate)
        val update=findViewById<Button>(R.id.modificar)
        val eliminar=findViewById<Button>(R.id.eliminar)
        val cancelar=findViewById<Button>(R.id.cancelarUpdate)
        cancelar.setOnClickListener {
            finish()
        }
        val bundle=intent.extras
        val id=bundle?.getString("id")
        val firstExtra=bundle?.getString("first")
        val lastExtra=bundle?.getString("last")
        first.setText(firstExtra)
        last.setText(lastExtra)

        eliminar.setOnClickListener {
            db.collection("users").document(id.toString())
                .delete()
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
            finish()
        }
        update.setOnClickListener {
            val city = hashMapOf(
                "first" to first.text.toString(),
                "last" to last.text.toString(),
            )
            db.collection("users").document(id.toString())
                .set(city)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
            finish()
        }
    }
}