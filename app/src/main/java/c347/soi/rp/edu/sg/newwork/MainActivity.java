package c347.soi.rp.edu.sg.newwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private EditText editText1;
    private EditText editText2;
    private TextView tv;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        tv = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);




        // Create relam instance
        Realm.init(this);
        realm = Realm.getDefaultInstance();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                save_into_database(editText1.getText().toString().trim(), Integer.parseInt(editText2.getText().toString().trim()));
                refresh_views();
            }
        });

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Create new Realm object
//                Person person = realm.createObject(Person.class);
//                person.setName("Xuan");
//                person.setAge(23);
            }
        });

        // Build the resut looking at all person:
        RealmResults<Person> personRealmObject = realm.where(Person.class).findAll();
        // See the outcome
        System.out.println(personRealmObject);
    }

    private void refresh_views() {
        //find out the things in database and can set the conditions
        RealmResults<Person> result2 = realm.where(Person.class)
                .equalTo("name", "John")
                .or()
                .equalTo("name", "Peter")
                .findAll();
    }

    private void save_into_database(final String name, final int age){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Person person = bgRealm.createObject(Person.class);
                person.setName(name);
                person.setAge(age);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                tv.v("Database added");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                tv.v("Database  not added");
            }
        });

    }
}
