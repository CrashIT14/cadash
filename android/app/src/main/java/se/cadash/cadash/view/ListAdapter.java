package se.cadash.cadash.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import se.cadash.cadash.R;
import se.cadash.cadash.model.Contact;

/**
 * Created by Albertsson on 15-05-16.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Contact> contactList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView view;
        public TextView deptView;
        public TextView indicator;
        public ViewHolder(View v) {
            super(v);
            view = (TextView) v.findViewById(R.id.name_Contact_list);
            deptView = (TextView) v.findViewById(R.id.deptListView);
            indicator = (TextView) v.findViewById(R.id.neagativeOrPositiveTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListAdapter(List<Contact> myDataset) {
        contactList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_object_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //--> HERE

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.setText(contactList.get(position).getFirstName());
        holder.deptView.setText(Integer.toString(contactList.get(position).getDebt()));
        if(contactList.get(position).getDebt() >= 0.0){
            holder.indicator.setText("+");
        }else{
            holder.indicator.setText("-");
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return contactList.size();
    }
}


