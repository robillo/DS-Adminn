package dailysuvichar.ds_admin.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dailysuvichar.ds_admin.R;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        String path = getIntent().getStringExtra("path");
        StorageReference gsReference = storage.getReferenceFromUrl(path);



        Glide.with(getApplicationContext())
                .using(new FirebaseImageLoader())
                .load(gsReference)
                .into((ImageView) findViewById(R.id.imageView));
    }
}
