package l3info.bonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView entry;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         entry = (TextView) findViewById(R.id.link);
         entry.setMovementMethod(LinkMovementMethod.getInstance());
         entry.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.link:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }


}
