package com.gamo.adimer.listafirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearActivity : AppCompatActivity() {
    private val TAG: String?="MYTAG"
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear)
        val first=findViewById<EditText>(R.id.first)
        val last=findViewById<EditText>(R.id.last)
        val crear=findViewById<Button>(R.id.crear)
        val cancelar=findViewById<Button>(R.id.cancelar)

        crear.setOnClickListener {
            val data = hashMapOf(
                "first" to first.text.toString(),
                "last" to last.text.toString()
            )

            db.collection("users")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.e(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error adding document", e)
                }
        }
        cancelar.setOnClickListener {
            finish()
        }
    }
}