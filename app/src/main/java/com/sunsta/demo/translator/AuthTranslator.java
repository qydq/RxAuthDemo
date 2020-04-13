package com.sunsta.demo.translator;


import com.sunsta.demo.AuthManager;
import com.sunsta.demo.augux.AuthAngux;
import com.sunsta.demo.action.AuthAction.SignUpAction;
import com.sunsta.demo.event.AuthEvent.SignUpEvent;
import com.sunsta.demo.result.AuthResult.SignUpResult;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


/**
 * Created by sunsta on 24/04/17.
 * <p>
 * 【Translator层】部分主要又ObservableTransformer组成。 将各个部件组装.
 */

public class AuthTranslator {
    private AuthManager authManager;
    private Subject<SignUpEvent> middle = PublishSubject.create();
    private Observable<AuthAngux> translator
            = middle.map(event -> new SignUpAction(event.getUsername(), event.getPassword()))
            //使用FlatMap转向，进行注册
            .flatMap(action -> authManager.signUp(action)
                    //扫描结果
                    .map(result -> {
                        if (result == SignUpResult.FAIL_USERNAME) {
                            return AuthAngux.fail(false, true, "Username error");
                        }
                        if (result == SignUpResult.FAIL_PASSWORD) {
                            return AuthAngux.fail(true, false, "Password error");
                        }
                        if (result == SignUpResult.SUCCESS) {
                            return AuthAngux.success();
                        }
                        //TODO Handle error
                        throw new IllegalArgumentException("Unknown Result");
                    })
                    //设置初始状态为loading。
                    .startWith(AuthAngux.inProcess())
                    //设置错误状态为error，防止触发onError() 造成断流
                    .onErrorReturn(error -> AuthAngux.fail(true, true, error.getMessage())))
            .replay(1)
            .autoConnect();

    public final ObservableTransformer<SignUpEvent, AuthAngux> signUp
            //上游是UiEvent，封装成对应的Action
            = observable -> {
        //中间人切换监听
        observable.subscribe(middle);
        return translator;
    };


    public AuthTranslator(AuthManager authManager) {
        this.authManager = authManager;
    }
}
