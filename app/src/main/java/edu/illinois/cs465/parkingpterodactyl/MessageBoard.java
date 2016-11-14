package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class MessageBoard extends AppCompatActivity {

    private int dpToPx(int dp) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(density * dp);
    }

    private void addMessageToBoard(Message m) {
         // Create the linear layout to hold the message
        LinearLayout messagesContainer = (LinearLayout)findViewById(R.id.content_message_board);

        LinearLayout newContainer = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(0, 0, 0, dpToPx(4));
        newContainer.setLayoutParams(layoutParams);
        newContainer.setOrientation(LinearLayout.HORIZONTAL);
        newContainer.setBackgroundResource(R.drawable.message_board_border);

         // Create the text view with the message text
        TextView messageText = new TextView(this);
        messageText.setText(m.getText());
        TableLayout.LayoutParams messageTextParams = new TableLayout.LayoutParams(
                0,
                TableLayout.LayoutParams.MATCH_PARENT,
                1f
        );
        messageText.setLayoutParams(messageTextParams);
        messageText.setGravity(Gravity.START);
        messageText.setTextColor(getResources().getColor(R.color.textColor));
        messageText.setPadding(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15));

        messagesContainer.addView(messageText);

         // Create the text view with the time text
        TextView messageTime = new TextView(this);
        messageTime.setText(m.getPastTime());
        TableLayout.LayoutParams messageTimeParams = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.MATCH_PARENT
        );
        messageTime.setLayoutParams(messageTimeParams);
        messageTime.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        messageTime.setTextColor(getResources().getColor(R.color.textColor));
        messageTime.setPadding(dpToPx(10), 0, dpToPx(10), 0);

        messagesContainer.addView(messageTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinkedList<Message> messages = new LinkedList<>();
        messages.add(new Message("Test message"));

        for (Message message : messages) {
            addMessageToBoard(message);
        }

    }

}
