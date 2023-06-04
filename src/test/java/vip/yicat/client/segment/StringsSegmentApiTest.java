package vip.yicat.client.segment;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.segment.model.AddStringSegmentRequest;
import vip.yicat.client.segment.model.ApiStringsSegment;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StringsSegmentApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String projectId = "2023032115450943900406279049";

    @Test
    @Order(1)
    void getI18NSegmentInfo() {
        String tagLan = "en-US";
        List<ApiStringsSegment> apiStringsSegments = client.getStringsSegmentApi().getI18NSegmentInfo(projectId, tagLan);
        System.out.println(apiStringsSegments);
        assertNotNull(apiStringsSegments);
    }

    @Test
    @Order(2)
    void addI18NSegmentInfo() {
        List<AddStringSegmentRequest> addStringSegmentRequestList = new ArrayList<>();
        AddStringSegmentRequest addStringSegmentRequest = new AddStringSegmentRequest();
        addStringSegmentRequest.setLanguage("zh-Hans");
        addStringSegmentRequest.setNote("这是一段描述");
        addStringSegmentRequest.setSourceText("这是一段中文文本789");
        addStringSegmentRequestList.add(addStringSegmentRequest);
        client.getStringsSegmentApi().addI18NSegmentInfo(projectId, addStringSegmentRequestList);
    }

    @Test
    @Order(3)
    void deleteI18NSegmentInfo() {
        List<String> segmentIdList = new ArrayList<>();
        segmentIdList.add("");
        segmentIdList.add("");
        client.getStringsSegmentApi().deleteI18NSegmentInfo(projectId, segmentIdList);
    }
}