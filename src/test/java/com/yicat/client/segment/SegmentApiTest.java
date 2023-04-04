package com.yicat.client.segment;

import com.yicat.client.Client;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.segment.model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yicat.client.sample.SubmitSegmentSample.buildApiSegmentEditMessage;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SegmentApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);
    String documentId = "91adc38ebe0648498847a7066bc5b507_en-US";
    String segmentId = client.getSegmentApi().loadSegmentByDocumentId(documentId, "").get(0).get_id();

    @Test
    @Order(1)
    void loadSegmentByDocumentId() {
        List<ApiSegment> apiSegments = client.getSegmentApi().loadSegmentByDocumentId(documentId, "");
        System.out.println(apiSegments);
        assertNotNull(apiSegments);
    }

    @Test
    @Order(2)
    void submitEditMessageByDocument() {
        ApiSegmentEditMessage apiSegmentEditMessage = buildApiSegmentEditMessage(documentId, segmentId);
        List<ApiSegmentEditMessage> apiSegmentEditMessageList = new ArrayList<>();
        apiSegmentEditMessageList.add(apiSegmentEditMessage);
        List<ApiSegmentEditMessage> apiSegmentEditMessages = client.getSegmentApi().submitEditMessageByDocument(documentId, apiSegmentEditMessageList);
        System.out.println(apiSegmentEditMessages);
        assertNotNull(apiSegmentEditMessages);
    }

    @Test
    @Order(3)
    void alignSegmentByDocumentId() {
        List<String> segmentIdList = new ArrayList<>();
        segmentIdList.add(segmentId);
        Map<String, List<Atom>> stringListMap = client.getSegmentApi().alignSegmentByDocumentId(documentId, segmentIdList);
        System.out.println(stringListMap);
    }

    @Test
    @Order(4)
    void loadSubmitHistoryByDocumentId() {
        List<ApiSegmentEditRevision> apiSegmentEditRevisions = client.getSegmentApi().loadSubmitHistoryByDocumentId(documentId, segmentId);
        System.out.println(apiSegmentEditRevisions);
    }

    @Test
    @Order(5)
    void getSegmentReferenceInfoByDocumentId() {
        List<ApiSegmentReferenceInfo> segmentReferenceInfoByDocumentIds = client.getSegmentApi().getSegmentReferenceInfoByDocumentId(documentId, segmentId);
        System.out.println(segmentReferenceInfoByDocumentIds);
        assertNotNull(segmentReferenceInfoByDocumentIds);
    }

    @Test
    @Order(6)
    void updateSegmentReferenceUrlByDocumentId() {
        String url = "https://www.tmxmall.com/";
        ApiSegmentReferenceInfo apiSegmentReferenceInfo = client.getSegmentApi().updateSegmentReferenceUrlByDocumentId(url, documentId, segmentId);
        System.out.println(apiSegmentReferenceInfo);
        assertNotNull(apiSegmentReferenceInfo);
       
    }

    @Test
    @Order(7)
    void updateSegmentReferenceImgUrlByDocumentId() {
        ApiSegmentReferenceInfo apiSegmentReferenceInfo = client.getSegmentApi().updateSegmentReferenceImgUrlByDocumentId(new File("C:\\Users\\86195\\Desktop\\sdk\\参考.png"), documentId, segmentId);
        System.out.println(apiSegmentReferenceInfo);
        assertNotNull(apiSegmentReferenceInfo);
    }

    @Test
    @Order(8)
    void cleanSegmentReferenceImgUrlByDocumentId() {
        ApiSegmentReferenceInfo apiSegmentReferenceInfo = client.getSegmentApi().cleanSegmentReferenceImgUrlByDocumentId(documentId, segmentId);
        System.out.println(apiSegmentReferenceInfo);
        assertNotNull(apiSegmentReferenceInfo);
       
    }

    @Test
    @Order(9)
    void revertSegmentByDocumentId() {
        client.getSegmentApi().revertSegmentByDocumentId(0, 16, documentId);
    }

}