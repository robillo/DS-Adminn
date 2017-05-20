package dailysuvichar.ds_admin.view.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dailysuvichar.ds_admin.R;
import dailysuvichar.ds_admin.model.Guru;
import dailysuvichar.ds_admin.view.holders.PendingGuruVH;

public class RVPendingGurus extends RecyclerView.Adapter<PendingGuruVH> {

    ArrayList<Guru> gurus;
    Context context;

    public RVPendingGurus(Context context, ArrayList<Guru> list){
        this.gurus=list;
        this.context=context;
    }
    @Override
    public PendingGuruVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_guru, parent, false);
        context = parent.getContext();
        return new PendingGuruVH(view);
    }

    @Override
    public void onBindViewHolder(final PendingGuruVH holder, final int position) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String sDob = "DOB: " +gurus.get(position).getDOB(),
                        sAge = "AGE: "+String.valueOf(gurus.get(position).getAge()),
                        sName = "NAME: "+gurus.get(position).getName(),
                        sEmail = "EMAIL: "+gurus.get(position).getEmail(),
                        sUid = "UID: " +gurus.get(position).getUid();
                holder.dob.setText(sDob);
                holder.age.setText(sAge);
                holder.name.setText(sName);
                holder.email.setText(sEmail);
                holder.uid.setText(sUid);
                holder.setGOV(context, gurus.get(position).getImgGov());
                holder.setSpec(context, gurus.get(position).getImgSpec());

            }
        });
    }

    @Override
    public int getItemCount() {
        return gurus.size();
    }
}
