package com.justsafe.just.vm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.justsafe.libview.base.BaseViewModel;

import javax.inject.Inject;

public class JUSTMeViewModel extends BaseViewModel {

    private static final String TAG = "EMCMeViewModel";

    private Context mContext;

    @Inject
    public JUSTMeViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application;
    }

    public MutableLiveData<String> error = new MutableLiveData<>();

    /**
     * 返回修改数量
     */
    public MutableLiveData<Integer> emcLoading = new MutableLiveData<>();

    /**
     * 返回修改数量
     */
    public MutableLiveData<Integer> emcEmcUserHospital = new MutableLiveData<>();

    /**
     * 返回修改数量
     */
    public MutableLiveData<Integer> emcEmcNickName = new MutableLiveData<>();

    /**
     * 判断错误或正常请求返回
     */
    public MutableLiveData<Integer> errorLiveVideoCount = new MutableLiveData<>();

    /**
     * 所有会议日程，范例请求
     */
/*    public void emcAllSchedule() {
        String emcUserGuid = EMCProfileManager.getInstance().getEMCUserGuid();
        String emcUserToken = EMCProfileManager.getInstance().getEMCUserToken();

        EMCRestClient.getApiUrl().allSchedule("Bearer " + emcUserToken, emcUserGuid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EmcScheduleResultBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmcScheduleResultBean emcScheduleResultBean) {
                        String code = emcScheduleResultBean.getCode();
                        List<EmcScheduleBean> data = emcScheduleResultBean.getData();
                        String errorInfo = emcScheduleResultBean.getErrorInfo();

                        if (code.equals(LiveDataEvent.EMC_SUCCESS)) {
                            //EMCProfileManager.getInstance().setEMCBannerCacheData(dataBeans);
                            emcScheduleBean.setValue(data);
                            errorLiveVideoCount.setValue(Constants.errorTwo);
                        } else {
                            error.setValue(errorInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue("请求失败");
                        errorLiveVideoCount.setValue(Constants.error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/

/*    *//**
     * 查询个人信息
     *//*
    public void emcUserInfo() {
        String userGuid = EMCProfileManager.getInstance().getEMCUserGuid();
        String authorization = "Bearer " + EMCProfileManager.getInstance().getEMCUserToken();

        Map<String, String> mDataMap = new HashMap<>();
        mDataMap.put(Constants.userGuid, EMCProfileManager.getInstance().getEMCUserGuid());
        mDataMap.put(Constants.Authorization, "Bearer " + EMCProfileManager.getInstance().getEMCUserToken());

        EMCRestClient.getApiUrl().information_interface(authorization, userGuid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EmcUserInfoResultBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EmcUserInfoResultBean emcUserInfoResultBean) {
                        String code = emcUserInfoResultBean.getCode();
                        EmcUserInfo data = emcUserInfoResultBean.getData();
                        String errorInfo = emcUserInfoResultBean.getErrorInfo();

                        if (code.equals(LiveDataEvent.EMC_SUCCESS)) {
                            //更新缓存
                            String userName = emcUserInfoResultBean.getData().getUserName();
                            String hospital = emcUserInfoResultBean.getData().getHospital();
                            String nickName = emcUserInfoResultBean.getData().getNickName();
                            EMCProfileManager.getInstance().setUserName(userName);
                            EMCProfileManager.getInstance().setEMCUserNickName(nickName);
                            EMCProfileManager.getInstance().setEMCUserHospital(hospital);
                            emcEmcUserInfo.setValue(data);
                            error.setValue("保存成功");
                        } else {
                            error.setValue(errorInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    *//**
     * 修改单位
     *//*
    public void emcUserHospital(String hospital) {
        String userGuid = EMCProfileManager.getInstance().getEMCUserGuid();
        String authorization = "Bearer " + EMCProfileManager.getInstance().getEMCUserToken();
        Map<String, Object> mDataMap = new HashMap<>();
        mDataMap.put(Constants.hospital, hospital);

        EMCRestClient.getApiUrl().updateEmcUnit_interface(authorization, userGuid, mDataMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EMCUserInfoHospitalBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EMCUserInfoHospitalBean emcUserInfoResultBean) {
                        String code = emcUserInfoResultBean.getCode();
                        Integer data = emcUserInfoResultBean.getData();
                        String errorInfo = emcUserInfoResultBean.getErrorInfo();
                        if (code.equals(LiveDataEvent.EMC_SUCCESS)) {
                            emcEmcUserHospital.setValue(data);
                            error.setValue("保存成功");
                            EMCProfileManager.getInstance().setEMCUserHospital(hospital);
                        } else {
                            error.setValue(errorInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    *//**
     * 修改昵称
     *//*
    public void emcUserNickName(String nickName) {
        String userGuid = EMCProfileManager.getInstance().getEMCUserGuid();
        String authorization = "Bearer " + EMCProfileManager.getInstance().getEMCUserToken();
        Map<String, Object> mDataMap = new HashMap<>();
        mDataMap.put(Constants.NickName, nickName);

        EMCRestClient.getApiUrl().updateEmcNickName_interface(authorization, userGuid, mDataMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EMCNickNameBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EMCNickNameBean nickNameBean) {
                        String code = nickNameBean.getCode();
                        Integer data = nickNameBean.getData();
                        String errorInfo = nickNameBean.getErrorInfo();
                        if (code.equals(LiveDataEvent.EMC_SUCCESS)) {
                            emcEmcNickName.setValue(data);
                            error.setValue("保存成功");
                            EMCProfileManager.getInstance().setEMCUserNickName(nickName);
                        } else {
                            error.setValue(errorInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue("请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }*/
}
