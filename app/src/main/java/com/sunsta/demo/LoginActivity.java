package com.sunsta.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.sunsta.demo.event.AuthEvent.SignUpEvent;
import com.sunsta.demo.translator.AuthTranslator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by sunsta on 2019/4/13.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.email_sign_in_button)
    Button rg;
    private CompositeDisposable disposables;
    private AuthTranslator translator;
    private Observable<String> stringObservable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        translator = ((AuthApplication) getApplication()).getTranslator();
        ButterKnife.bind(this);
        disposables = new CompositeDisposable();
    }

    @Override
    protected void onStart() {
        super.onStart();


        /*
         * (1)将Event打包(这里我使用RxBinding)
         * */
        Observable<SignUpEvent> click = RxView.clicks(rg)
                .map(ignore -> new SignUpEvent(mEmailView.getText()
                        .toString(), mPasswordView.getText().toString()));

        /*
         * (2)将各个部分通过Translator组装
         * */
        disposables.add(click.compose(translator.signUp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(angux -> {
                    //载入进度条
                    mProgressView.setVisibility(angux.isInProcess() ? View.VISIBLE : View.GONE);
                    //判断用户名/密码是否合法
                    if (!angux.isPwdValidate()) {
                        mPasswordView.setError(angux.getErrorMessage());
                    } else {
                        mPasswordView.setError(null);
                    }
                    if (!angux.isUsrValidate()) {
                        mEmailView.setError(angux.getErrorMessage());
                    } else {
                        mEmailView.setError(null);
                    }
                    //是否成功
                    if (angux.isSuccess()) {
                        Toast.makeText(this, "CreateUser SuccessFull", Toast.LENGTH_SHORT).show();
                    }
                }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

}
