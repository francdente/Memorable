package fr.eurecom.memorable;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import fr.eurecom.memorable.R;
import fr.eurecom.memorable.home.RecyclerViewAdapter;
import fr.eurecom.memorable.home.contentNodes.ContentNode;

public class DetailsActivity extends AppCompatActivity {
    ContentNode node;
    RecyclerViewAdapter customAdapter;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null){
            node = (ContentNode) intent.getSerializableExtra("node");
        }
        setContentView(R.layout.details_activity);
        linearLayout = findViewById(R.id.linear_layout);
        linearLayout.addView(node.createDetailedView(this));
    }

}
