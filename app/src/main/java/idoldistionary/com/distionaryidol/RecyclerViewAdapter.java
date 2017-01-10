package idoldistionary.com.distionaryidol;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sak on 1/10/17.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DictionaryHolder> {

    private Cursor cursor;
    private RecyclerAdapterListener mRecyclerAdapterListener;

    public void setCursor(Cursor cursor){
        this.cursor = cursor;
        notifyDataSetChanged();
    }
    public RecyclerViewAdapter(){

    }
    public void setmRecyclerAdapterListener(RecyclerAdapterListener mRecyclerAdapterListener){
        this.mRecyclerAdapterListener = mRecyclerAdapterListener;
    }

    //DictionaryHolder Class
    public class DictionaryHolder extends RecyclerView.ViewHolder {
        private TextView vocabularyword;
        public DictionaryHolder(View itemView) {
            super(itemView);
            vocabularyword = (TextView)itemView.findViewById(R.id.itemshow);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    cursor.moveToPosition(position);
                    String word = cursor.getString(0);
                    String definition = cursor.getString(1);
                    mRecyclerAdapterListener.onItemClicked(definition);
                    Log.d("Adapter", "clicked " + getLayoutPosition() + " word:" + word);

                }
            });
        }
    }

    @Override
    public DictionaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.itme_recyclerview_show,parent,false);
        // Return a new holder instance

        return new DictionaryHolder(rootView);
    }

    @Override
    public void onBindViewHolder(DictionaryHolder holder, int position) {
        cursor.moveToPosition(position);
        String word = cursor.getString(0);
        //set item
        TextView textshow = holder.vocabularyword;
        textshow.setText(word);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }
}
