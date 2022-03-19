package com.zaqbest.study.foundation.dp.creation.prototype;

public class Setup {
    public static void main(String[] args) {
        CookieMachine cookieMachine = new CookieMachine();
        cookieMachine.setCookie(new Cookie());

        for (int i = 0; i < 100; i++){
            cookieMachine.makeCookie();
        }
    }
}
