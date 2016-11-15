package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.LinkedList;

public class MessageBoardFragment extends Fragment {

    public MessageBoardFragment() {
        // Required empty constructor
    }

    private int dpToPx(int dp) {
        float density = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(density * dp);
    }

    private void addMessageToBoard(Message m) {
         // Create the linear layout to hold the message
        LinearLayout messagesContainer = (LinearLayout)getActivity().findViewById(R.id.content_message_board);

        LinearLayout newContainer = new LinearLayout(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, dpToPx(4));
        newContainer.setLayoutParams(layoutParams);
        newContainer.setOrientation(LinearLayout.HORIZONTAL);
        newContainer.setBackgroundResource(R.drawable.message_board_border);

         // Create the text view with the message text
        TextView messageText = new TextView(getActivity());
        messageText.setText(m.getText());
        LinearLayout.LayoutParams messageTextParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        messageText.setLayoutParams(messageTextParams);
        messageText.setGravity(Gravity.START);
        messageText.setTextColor(getResources().getColor(R.color.textColor));
        messageText.setPadding(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15));

        newContainer.addView(messageText);

         // Create the text view with the time text
        TextView messageTime = new TextView(getActivity());
        messageTime.setText(m.getPastTime());
        LinearLayout.LayoutParams messageTimeParams = new LinearLayout.LayoutParams(
                dpToPx(50),
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        messageTime.setLayoutParams(messageTimeParams);
        messageTime.setGravity(Gravity.CENTER);
        messageTime.setTextColor(getResources().getColor(R.color.textColor));
        messageTime.setPadding(dpToPx(10), 0, dpToPx(10), 0);

        newContainer.addView(messageTime);

        messagesContainer.addView(newContainer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_board, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Message Board");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new AddMessageFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // TODO - remove the messages when the action is paused and add them when it becomes visible. This will cause the times to be updated.
        LinkedList<Message> messages = new LinkedList<>();
        messages.add(new Message("Test message."));
        messages.add(new Message("A second test message that is a bit longer. This message is supposed to show word wrapping."));
        messages.add(new Message("Let's"));
        messages.add(new Message("See"));
        messages.add(new Message("If"));
        messages.add(new Message("Scrolling"));
        messages.add(new Message("Works"));
        messages.add(new Message("Or"));
        messages.add(new Message("Not"));

        for (Message message : messages) {
            addMessageToBoard(message);
        }
    }

}
