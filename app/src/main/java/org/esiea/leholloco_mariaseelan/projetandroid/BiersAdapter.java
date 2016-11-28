package org.esiea.leholloco_mariaseelan.projetandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gerard on 15/11/2016.
 */

class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

    private JSONArray biers;

    public BiersAdapter(JSONArray json){
        this.biers=json;
    }

    public void setNewBiere(JSONArray json){
        notifyDataSetChanged();
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BierHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element,parent,false));
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        try {
            JSONObject json= biers.getJSONObject(position);
            holder.name.setText(json.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return biers.length();
    }

    class BierHolder extends RecyclerView.ViewHolder {

        public TextView name ;

        public BierHolder(View itemView) {
            super(itemView);
            this.name= (TextView) itemView.findViewById(R.id.rv_bier_element_name);
        }
    }


}

