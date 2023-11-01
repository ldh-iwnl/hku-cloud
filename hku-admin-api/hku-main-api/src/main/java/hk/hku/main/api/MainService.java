package hk.hku.main.api;

import hk.hku.common.core.api.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 余胜军
* @ClassName MainService
* @qq 644064779
* @addres www.mayikt.com
* 微信:yushengjun644
*/
public interface MainService {
    /**
    * 测试main接口
    *
    * @return
    */
    @RequestMapping("/main")
    String main();

    @RequestMapping("/main2")
    BaseResponse<String> main2();

    @RequestMapping("/main3")
    BaseResponse<String> main3(int age);

}