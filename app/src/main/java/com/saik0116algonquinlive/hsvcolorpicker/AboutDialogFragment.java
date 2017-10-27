package com.saik0116algonquinlive.hsvcolorpicker;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by joshsaikaly on 2017-10-
 * 026.
 */

public class AboutDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Decorate our About dialog
        builder.setTitle(R.string.action_about)

                .setMessage(R.string.author)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
