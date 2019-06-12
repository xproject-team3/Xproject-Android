//선택한 호수의 우편함에 접근하기 위해 비밀번호를 입력하는 화면이다.
package com.example.txt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //비밀번호를 입력하는 editText 호출
        final EditText editText = (EditText) findViewById(R.id.edittext);
        //입력된 string과 비밀번호를 비교하는 login버튼 호출
        Button button = (Button) findViewById(R.id.button);
        final TextView textView = (TextView) findViewById(R.id.textview);

        //버튼이 클릭되었을 때 입력된 string과 비밀번호를 비교
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userPassword = editText.getText().toString();
                //비밀번호가 일치하면 아래 텍스트로 일치함을 보여주며 우편함 기록 화면(Main2Activity)으로 이동
                if(userPassword.equals("1234")) {
                    textView.setText("일치");
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(intent);
                }
                //비밀번호가 일치하지 않으면 아래 다시 입력을 요청하는 텍스트 띄움
                else {
                    textView.setText("비밀번호를 다시 입력하세요");
                }
            }
        });

    }
}
