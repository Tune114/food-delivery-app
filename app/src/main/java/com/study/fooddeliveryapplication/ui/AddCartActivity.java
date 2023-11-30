package com.study.fooddeliveryapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.ImageView;
import android.widget.TextView;

=======
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
>>>>>>> master
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCartItemAdapter;
=======
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.fooddeliveryapplication.R;
import com.study.fooddeliveryapplication.adapter.ListCartItemAdapter;
import com.study.fooddeliveryapplication.model.ModelCart;
import com.study.fooddeliveryapplication.model.ModelOrder;
>>>>>>> master

import java.util.ArrayList;
import java.util.List;

public class AddCartActivity extends AppCompatActivity {

    private RecyclerView rvListItem;
<<<<<<< HEAD
    private TextView txtAddPayment;

    private ImageView btnBack;
=======
    private Button btnPlaceOrder;
    private TextView txtAddPayment, txPayable;
    private ListCartItemAdapter listCartItemAdapter;
    private ImageView btnBack;
    private  List<ModelCart> listOrder;
    private ModelOrder modelOrder;
>>>>>>> master
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

<<<<<<< HEAD
        SeedData();

=======
        // listCartItemAdapter
        rvListItem = findViewById(R.id.listCartItem);
        rvListItem.setLayoutManager(new LinearLayoutManager(this ));
        FirebaseRecyclerOptions<ModelCart> order =
                new FirebaseRecyclerOptions.Builder<ModelCart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ItemCart"), ModelCart.class)
                        .build();
        listCartItemAdapter = new ListCartItemAdapter(order);
        rvListItem.setAdapter(listCartItemAdapter);

        // txPayable and ModelOrder modelOrder
        listOrder = new ArrayList<>();
        modelOrder = new ModelOrder();
        txPayable = (TextView) findViewById(R.id.txtPayable);
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("ItemCart");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int payable =0;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelCart modelCart  = dataSnapshot.getValue(ModelCart.class);
                    payable = payable + Integer.parseInt(modelCart .getPrice())*Integer.parseInt(modelCart .getQuantity());
                    listOrder.add(modelCart );
                }
                txPayable.setText(String.valueOf(payable));
                modelOrder.setListFood(listOrder);
                modelOrder.setPayable(String.valueOf(payable));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // btnPlaceOrder
        btnPlaceOrder = (Button)findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Order")
                        .push().setValue(modelOrder.toMap()).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddCartActivity.this, "Failse to order", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                //  FirebaseDatabase.getInstance().getReference().child("ItemCart").removeValue();
                                Toast.makeText(AddCartActivity.this, "Success to add", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // txtAddPayment
>>>>>>> master
        txtAddPayment = findViewById(R.id.txtEditPayment);
        txtAddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });

<<<<<<< HEAD
=======
        // btnBack
>>>>>>> master
        btnBack= (ImageView)findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(AddCartActivity.this, HomePageActivity.class);
                startActivity(intent);

            }
        });
<<<<<<< HEAD
    }

    public void SeedData(){
        List<String> list = new ArrayList<>();

        list.add("Margherita");
        list.add("Pepperoni");
        list.add("Mushroom");
        rvListItem = findViewById(R.id.listCartItem);
        rvListItem.setLayoutManager(new LinearLayoutManager(this ));
        ListCartItemAdapter listCartItemAdapter = new ListCartItemAdapter(list);
        rvListItem.setAdapter(listCartItemAdapter);
    }
}
=======

    }



    @Override
    protected void onStart() {
        super.onStart();
        listCartItemAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        listCartItemAdapter.stopListening();
    }

}
>>>>>>> master
