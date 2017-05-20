package dailysuvichar.ds_admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;


/**
 * Created by rishabhshukla on 20/05/17.
 */

class PendingGuruVH extends RecyclerView.ViewHolder {
    TextView name,email,uid,dob,age;
    Button reject, accept;
    ImageView imgGOV, imgSPEC;
    public PendingGuruVH(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        email = (TextView) itemView.findViewById(R.id.email);
        uid = (TextView) itemView.findViewById(R.id.uid);
        dob = (TextView) itemView.findViewById(R.id.dob);
        age = (TextView) itemView.findViewById(R.id.age);
        reject = (Button) itemView.findViewById(R.id.reject);
        accept = (Button) itemView.findViewById(R.id.accept);
        imgGOV= (ImageView) itemView.findViewById(R.id.imgGov);
        imgSPEC= (ImageView) itemView.findViewById(R.id.imgSpec);
    }

    public void setGOV(Context ctx, StorageReference storageReference){
        if(storageReference!=null) {
            Log.e("sfae",storageReference.toString());
            Glide.with(ctx).
                    using(new FirebaseImageLoader())
                    .load(storageReference)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgGOV);
        }
    }
    public void setSpec(Context ctx, StorageReference storageReference){
        if(storageReference!=null) {
            Log.e("sfae",storageReference.toString());

            Glide.with(ctx).
                    using(new FirebaseImageLoader())
                    .load(storageReference)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgSPEC);
        }
    }
}
