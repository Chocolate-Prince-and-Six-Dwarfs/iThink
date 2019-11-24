package com.choco.ithink;
import com.choco.ithink.DAO.mapper.BbsTopicMapper;
import com.choco.ithink.pojo.BbsTopic;
import com.choco.ithink.pojo.BbsTopicExample;
import com.choco.ithink.service.CreativeIdeaService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.exception.CouldNotConnectException;
import io.searchbox.indices.DeleteIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


@Component
public class AfterRunning implements ApplicationRunner {
    @Resource
    private BbsTopicMapper bbsTopicMapper;
    @Resource
    private JestClient jestClient;
    @Autowired
    private CreativeIdeaService creativeIdeaService;

    @Override
    public void run(ApplicationArguments args) throws CouldNotConnectException {
        initIndex();
    }

    private boolean deleteIndex()
    {
//        Delete.Builder builder = new Delete.Builder();
//        //builder.setHeader(PWDKEY, getSecret());
//        builder.refresh(true);
//        Delete delete = builder.index("bbs_topic-index").build();
        DeleteIndex delete = new DeleteIndex.Builder(BbsTopic.INDEX).refresh(true).build();
        try {
            JestResult result = jestClient.execute(delete);
            if (result != null && result.isSucceeded()) {
                throw new RuntimeException(result.getErrorMessage()+"删除索引失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void initIndex()
    {
        deleteIndex();

        List<BbsTopic> bbsTopicList = bbsTopicMapper.selectByExample(new BbsTopicExample());
        if(bbsTopicList.size()>0)
        {
            creativeIdeaService.saveBbsTopic(bbsTopicList);
        }
    }
}
