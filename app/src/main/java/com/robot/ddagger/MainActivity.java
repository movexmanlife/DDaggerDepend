package com.robot.ddagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.robot.ddagger.bean.Student;
import com.robot.ddagger.constructorcomponent.ConstructorModule;
import com.robot.ddagger.constructorcomponent.DaggerConstructorComponent;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Inject
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);

        DaggerConstructorComponent.builder().appComponent(((App)getApplication()).getAppComponent())
                .constructorModule(new ConstructorModule()).build().inject(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        Toast.makeText(getApplicationContext(), student.getName() + ", " + student.getAge(), Toast.LENGTH_SHORT).show();
    }
}
