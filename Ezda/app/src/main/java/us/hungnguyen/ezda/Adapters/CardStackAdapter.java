package us.hungnguyen.ezda.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import us.hungnguyen.ezda.R;

public class CardStackAdapter extends ArrayAdapter<String> {
    public CardStackAdapter(Context context, int resource) {
        super(context, 0);
    }

    @Override
    public View getView(int position, final View contentView, ViewGroup parent) {
        //supply the layout for your card
        ImageView v = (ImageView) (contentView.findViewById(R.id.cardstack_image));
        TextView name = (TextView) (contentView.findViewById(R.id.cardstack_username));
        name.setText(getItem(position));
        return contentView;
    }

}
