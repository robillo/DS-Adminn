package dailysuvichar.ds_admin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


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
}
