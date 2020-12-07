package com.example.minesweeper.UI;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.minesweeper.R;


/**
 * Shows congratulations message at the end of the game
 * when the user has found all the number of zombies.
 */
public class MessageFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout, null);

        // Create a button Listener

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();

            }
        };


        // Build the alert dialog
        return new AlertDialog.Builder(getActivity())
                .setTitle(" ")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }

}