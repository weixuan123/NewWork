package c347.soi.rp.edu.sg.newwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create relam instance
        Realm.init(this);
        realm = Realm.getDefaultInstance();

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
}
