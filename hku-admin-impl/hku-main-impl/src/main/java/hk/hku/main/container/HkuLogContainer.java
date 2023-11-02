package hk.hku.main.container;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author kyleli
 * @ClassName HkuLogContainer
 */
@Component
public class HkuLogContainer {
    private LinkedBlockingDeque<AsyncLogWrapper> linkedBlockingDeque= new LinkedBlockingDeque<AsyncLogWrapper>();

    public void addLog(AsyncLogWrapper asyncLogWrapper){
        linkedBlockingDeque.offer(asyncLogWrapper);
    }

    public LinkedBlockingDeque<AsyncLogWrapper> getAsynLogWrapperDeque(){
        return linkedBlockingDeque;
    }
}
