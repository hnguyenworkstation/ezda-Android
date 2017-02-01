package us.hungnguyen.ezda.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import us.hungnguyen.ezda.Adapters.CardStackAdapter;
import us.hungnguyen.ezda.Animations.RippleAnimation;
import us.hungnguyen.ezda.CardDesigns.CardStack;
import us.hungnguyen.ezda.CardDesigns.DefaultStackEventListener;
import us.hungnguyen.ezda.R;

public class ConnectFragment extends Fragment {

    RippleAnimation rippleBackground1, rippleBackground2;
    CardStack cardStack;
    CardStackAdapter mCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        init(view);
        doRippleBackground();
        return view;
    }

    private void init(View view) {
        //root ripple background initialization
        rippleBackground1 = (RippleAnimation) view.findViewById(R.id.content);
        cardStack = (CardStack) view.findViewById(R.id.frame);

        //at begin setting rippleBackground visibility as VISIBLE and setting CardStack visibility as GONE
        rippleBackground1.setVisibility(View.VISIBLE);
        cardStack.setVisibility(View.GONE);

        //creating adapter
        mCardAdapter = new CardStackAdapter(getActivity().getApplicationContext(), 0);
    }

    public void doRippleBackground() {
        startAnimation();

        //handler created to handle cardStack as well as timer...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                callCardStack();
            }
        }, 8000);

    }

    //start the background ripple animation...
    private void startAnimation() {
        //if it's not running
        if (!rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.startRippleAnimation();//start root ripple animation
            // rippleBackground2.startRippleAnimation();//start child ripple animation
        }
    }

    //this method will stop background ripple animation. if it's running.
    private void stopAnimation() {
        if (rippleBackground1.isRippleAnimationRunning()) {
            rippleBackground1.stopRippleAnimation();
            // rippleBackground2.stopRippleAnimation();
        }
    }

    //cardStack view will set it as visible and load the information into stack.
    public void callCardStack() {

        cardStack.setVisibility(View.VISIBLE);
        rippleBackground1.setVisibility(View.GONE);

        stopAnimation();//start the ripple background animation.

        //Setting Resource of CardStack
        cardStack.setContentResource(R.layout.card_stack_item);

        //Adding 30 dummy info for CardStack
        for (int i = 0; i <= 30; i++)
            mCardAdapter.add("" + i);
        cardStack.setAdapter(mCardAdapter);

        //Setting Listener and passing distance as a parameter ,
        //based on the distance card will discard
        //if dragging card distance would be more than specified distance(100) then card will discard or else card will reverse on same position.
        cardStack.setListener(new DefaultStackEventListener(300));

    }
}
