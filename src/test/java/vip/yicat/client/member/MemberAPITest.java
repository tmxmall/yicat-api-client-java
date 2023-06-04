package vip.yicat.client.member;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.member.model.ApiTranslationGroupMember;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemberAPITest {

    @Test
    void getTranslationGroupMember() {
        Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
        Client client = new Client(credentials);
        List<ApiTranslationGroupMember> data = client.getMemberAPI().getTranslationGroupMember();
        System.out.println(data);
        assertNotNull(data);
    }
}