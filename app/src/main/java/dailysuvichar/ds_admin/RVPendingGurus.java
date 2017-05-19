package dailysuvichar.ds_admin;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by rishabhshukla on 20/05/17.
 */

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
                holder.dob.setText(gurus.get(position).getDOB());
                holder.age.setText(gurus.get(position).getAge());
                holder.name.setText(gurus.get(position).getName());
                holder.email.setText(gurus.get(position).getEmail());
                holder.uid.setText(gurus.get(position).getUid());

            }
        });
    }

    @Override
    public int getItemCount() {
        return gurus.size();
    }
}
