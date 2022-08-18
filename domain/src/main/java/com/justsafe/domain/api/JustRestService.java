package com.justsafe.domain.api;
public interface JustRestService {

    String PUCLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6VFSyc5c5kPQDf1I1Y3LZKV5qFnjZsJNqMgK0PJY5O2hj8hDqkBlE9JAkEDfGsB2s5FiqoUIiGykrvg6qUjK27oE5KaUiMGAVs9ZO8teLsrgvnthNCJkwbWQMhWpsAwfrSEMNL1AhhUeXGQyH30P3mrNdFVqN1nq64fVoQfTx3evIwr8aN1472wIrMzkvCthxU7fNP+6PFPXm/ylcBtfq5j9eug856jc95EdOcQJ6wZ4w5DEC88aRy0jFgnmhuvP/GpbZ4XyvlkaN2Z/KbhXg0yV5jlgELwdaSKNOCIgXN4UjAivJgfnaaVT0dvUhHv1cGH4UHC2F1np+FFnsi9sdwIDAQAB";
    /**
     * @param header
     * @param map    1.账号密码登录
     *               {
     *               "userPhone":"13815456451",
     *               "pwd":"e8dd60281edea8157cce2a887448a21b",
     *               "platform":"pc",
     *               "elabUser":1
     *               }
     *               2.短信登陆
     *               {
     *               "userPhone":"13815456451",
     *               "smsCode":"000000",
     *               "platform":"android",
     *               "elabUser":1
     *               }
     * @return
     */

//    样本代码
//    @POST("User/SignIn")
//    Observable<LoginResultBean> loginByAccount(@HeaderMap Map<String, String> header, @Body Map<String, Object> map);

}
