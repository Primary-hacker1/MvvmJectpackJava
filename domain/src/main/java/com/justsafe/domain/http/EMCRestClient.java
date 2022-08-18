package com.justsafe.domain.http;


import android.os.Environment;

import androidx.annotation.NonNull;


import com.justsafe.domain.api.BaseApi;
import com.justsafe.domain.api.JustRestService;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class EMCRestClient {
    private static JustRestService restService;

    public static JustRestService getApiUrl() {
        if (restService == null) {
            synchronized (JustRestService.class) {
                if (restService == null) {
                    restService = new EMCRestClient().getRetrofit();
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
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BaseApi.HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient initOkHttp() {
        int cacheSize = 10 * 1024 * 1024;  // 10 MiB
        final String CACHE_PATH
                = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/emc/OkHttp/cache";
        File cacheDirectory = new File(CACHE_PATH);

        Cache cache = new Cache(cacheDirectory, cacheSize);

        return new OkHttpClient().newBuilder()
                .cache(cache)
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(10, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();

    }
}