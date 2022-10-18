package com.gamo.adimer.listafirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val TAG: String?="MYLOG"
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listView=findViewById<ListView>(R.id.listView)
        var btnCrear=findViewById<Button>(R.id.btnCrear)

        btnCrear.setOnClickListener {
            val intent=Intent(this,CrearActivity::class.java)
            startActivity(intent)
        }

        db.collection("users")
//            .whereEqualTo("state", "CA")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.e(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val cities = ArrayList<String>()
                var list= mutableListOf<Model>()
                for (doc in value!!) {
//                    doc.getString("first")?.let {
//                        cities.add(it)
//                    }
                    list.add(Model(doc.getString("first").toString(),doc.getString("last").toString(),doc.id))
                }
                listView.adapter=MyListAdapter(this,R.layout.row,list)
//                Log.e(TAG, "Current cites in CA: $cities")
            }

    }
}