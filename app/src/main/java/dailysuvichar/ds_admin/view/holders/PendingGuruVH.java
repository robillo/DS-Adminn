package dailysuvichar.ds_admin.view.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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

import dailysuvichar.ds_admin.R;
import dailysuvichar.ds_admin.view.activities.FullScreenActivity;

public class PendingGuruVH extends RecyclerView.ViewHolder {

    public TextView name,email,uid,dob,age;
    public CardView cardView;
    public ImageView imgGOV, imgSPEC;
    private Context context;
    private StorageReference storageReference1, storageReference2;

    public PendingGuruVH(View itemView) {
        super(itemView);
        context = itemView.getContext();
        name = (TextView) itemView.findViewById(R.id.name);
        email = (TextView) itemView.findViewById(R.id.email);
        uid = (TextView) itemView.findViewById(R.id.uid);
        dob = (TextView) itemView.findViewById(R.id.dob);
        age = (TextView) itemView.findViewById(R.id.age);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        imgGOV= (ImageView) itemView.findViewById(R.id.imgGov);
        imgSPEC= (ImageView) itemView.findViewById(R.id.imgSpec);
    }

    public void setGOV(Context ctx, StorageReference storageReference){
        if(storageReference!=null) {
            this.storageReference1 = storageReference;
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
            this.storageReference1 = storageReference;
            Log.e("sfae",storageReference.toString());
            Glide.with(ctx).
                    using(new FirebaseImageLoader())
                    .load(storageReference)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgSPEC);
        }
    }

    public void fullScreenIntent(){
        Intent i = new Intent(context, FullScreenActivity.class);
        i.putExtra("path", storageReference1.toString());
        i.putExtra("path", storageReference2.toString());
        Log.e("Storage Reference", storageReference1.toString());
        Log.e("Storage Reference", storageReference2.toString());
        context.startActivity(i);
    }
}
