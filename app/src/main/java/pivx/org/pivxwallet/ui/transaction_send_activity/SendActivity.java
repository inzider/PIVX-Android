package pivx.org.pivxwallet.ui.transaction_send_activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pivx.org.pivxwallet.R;
import pivx.org.pivxwallet.contacts.Contact;
import pivx.org.pivxwallet.ui.base.BaseActivity;

/**
 * Created by Neoperol on 5/4/17.
 */

public class SendActivity extends BaseActivity {
    final Context context = this;
    private Button buttonSend;
    private AutoCompleteTextView edit_address;
    private EditText edit_name;
    private MyFilterableAdapter filterableAdapter;
    @Override
    protected void onCreateView(Bundle savedInstanceState,ViewGroup container) {
        getLayoutInflater().inflate(R.layout.fragment_transaction_send, container);
        setTitle("Send");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        buttonSend = (Button) findViewById(R.id.btnSend);
        edit_address = (AutoCompleteTextView) findViewById(R.id.edit_address);
        edit_name = (EditText) findViewById(R.id.edit_name);
        buttonSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // create a Dialog component
                final Dialog dialog = new Dialog(context);

                //tell the Dialog to use the dialog.xml as it's layout description
                dialog.setContentView(R.layout.transaction_dialog);
                dialog.setTitle("Android Custom Dialog Box");

                TextView valuePivx = (TextView) dialog.findViewById(R.id.valuePivx);

                valuePivx.setText("valuePivx");

                Button dialogButton = (Button) dialog.findViewById(R.id.btnConfirm);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                //Grab the window of the dialog, and change the width
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
                //This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // todo: This is not updating the filter..
        if (filterableAdapter==null) {
            List<Contact> list = new ArrayList<>(pivxModule.getContacts());
            filterableAdapter = new MyFilterableAdapter(this,list );
            edit_address.setAdapter(filterableAdapter);
        }
    }
}
