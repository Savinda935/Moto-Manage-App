package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Save.setOnClickListener{
            val ownerName=binding.UploadEnterOwnerName.text.toString()
            val vehicleBrand=binding.UploadEnterVehicleBrand.text.toString()
            val vehicleRTO=binding.UploadEnterVehicleRTO.text.toString()
            val vehicleNumber=binding.UploadEnterVehicleRTO.text.toString()

            databaseReference=FirebaseDatabase.getInstance().getReference("vehicle Information")
            val vehicleData=VehicleData(ownerName,vehicleBrand,vehicleRTO,vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.UploadEnterOwnerName.text.clear()
                binding.UploadEnterVehicleBrand.text.clear()
                binding.UploadEnterVehicleRTO.text.clear()
                binding.UploadEnterVehiclenumber.text.clear()

                Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show()
                val intent=Intent(this@UploadActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }


        }

    }
}