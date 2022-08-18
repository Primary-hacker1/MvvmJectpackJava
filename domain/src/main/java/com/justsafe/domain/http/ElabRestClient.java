package com.justsafe.domain.http;


import androidx.annotation.NonNull;

import com.justsafe.domain.api.BaseApi;
import com.justsafe.domain.api.JustRestService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ElabRestClient {
    private static JustRestService restService;

    public static JustRestService getApiUrl() {
        if (restService == null) {
            synchronized (ElabRestClient.class) {
                if (restService == null) {
                    restService = new ElabRestClient().getRetrofit();
                }
            }
        }
        return restService;
    }

    public JustRestService getRetrofit() {
        JustRestService restService = initRetrofit(initOkHttp()).create(JustRestService.class);
        return restService;
    }

    @NonNull
    private Retrofit initRetrofit(OkHttpClient client) {
        String url = BaseApi.HOST;
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(8, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(8, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(8, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();

    }
}