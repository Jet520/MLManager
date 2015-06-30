package com.javiersantos.mlmanager.async;

import android.content.Context;
import android.os.AsyncTask;

import com.afollestad.materialdialogs.MaterialDialog;
import com.javiersantos.mlmanager.R;
import com.javiersantos.mlmanager.utils.UtilsDialog;
import com.javiersantos.mlmanager.utils.UtilsRoot;

public class DeleteDataInBackground extends AsyncTask<Void, String, Boolean> {
    private Context context;
    private MaterialDialog dialog;
    private String directory;
    private String successTitle;
    private String successDescription;

    public DeleteDataInBackground(Context context, MaterialDialog dialog, String directory, String successTitle, String successDescription) {
        this.context = context;
        this.dialog = dialog;
        this.directory = directory;
        this.successTitle = successTitle;
        this.successDescription = successDescription;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean status = UtilsRoot.removeWithRootPermission(directory);
        return status;
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);
        dialog.dismiss();
        if (status) {
            UtilsDialog.showTitleContent(context, successTitle, successDescription);
        } else {
            UtilsDialog.showTitleContent(context, context.getResources().getString(R.string.dialog_cache_and_data_error), context.getResources().getString(R.string.dialog_cache_and_data_error_description));
        }
    }
}