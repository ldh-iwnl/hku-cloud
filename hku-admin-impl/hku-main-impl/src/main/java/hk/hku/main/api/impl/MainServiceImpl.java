package hk.hku.main.api.impl;


import hk.hku.common.core.api.BaseApiService;
import hk.hku.common.core.api.BaseResponse;
import hk.hku.main.api.MainService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余胜军
 * @ClassName MainServiceImpl
 * @qq 644064779
 * @addres www.mayikt.com
 * 微信:yushengjun644
 */
@RestController
public class MainServiceImpl extends BaseApiService implements MainService {
    @Override
    public String main() {
        return "ok";
    }

    @Override
    public BaseResponse<String> main2() {
        return setResultSuccessData("ok");
    }

    @Override
    public BaseResponse<String> main3(int age) {
        int j = 1/age;
        return setResultSuccessData(j);
    }
}