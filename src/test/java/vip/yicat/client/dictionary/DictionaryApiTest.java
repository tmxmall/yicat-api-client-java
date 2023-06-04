package vip.yicat.client.dictionary;

import vip.yicat.client.Client;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.dictionary.model.AddTranslationDictionaryEntryRequest;
import vip.yicat.client.dictionary.model.ApiTranslationDictionary;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DictionaryApiTest {
    Credentials credentials = new Credentials("c633e36d5c1247f493e46507d5c61994");
    Client client = new Client(credentials);

    String dictionaryId = client.getDictionaryApi().listDictionary().get(0).getDictionaryId();

    @Test
    @Order(1)
    void createDictionary() {
        ApiTranslationDictionary apiTranslationDictionary = client.getDictionaryApi().createDictionary("zh-Hans", "牛津词典22");
        System.out.println(apiTranslationDictionary);
        assertNotNull(apiTranslationDictionary);
    }

    @Test
    @Order(2)
    void listDictionary() {
        List<ApiTranslationDictionary> apiTranslationDictionaryList = client.getDictionaryApi().listDictionary();
        System.out.println(apiTranslationDictionaryList);
        assertNotNull(apiTranslationDictionaryList);
    }

    @Test
    @Order(3)
    void dictionaryDetail() {
        ApiTranslationDictionary apiTranslationDictionary = client.getDictionaryApi().dictionaryDetail(dictionaryId);
        System.out.println(apiTranslationDictionary);
        assertNotNull(apiTranslationDictionary);
    }

    @Test
    @Order(4)
    void updateDictionary() {
        ApiTranslationDictionary apiTranslationDictionary = client.getDictionaryApi().updateDictionary(dictionaryId, "牛津词典33");
        System.out.println(apiTranslationDictionary);
        assertNotNull(apiTranslationDictionary);
    }

    @Test
    @Order(5)
    void insertEntry() {
        AddTranslationDictionaryEntryRequest addTranslationDictionaryEntryRequest = new AddTranslationDictionaryEntryRequest();
        addTranslationDictionaryEntryRequest.setEntryName("entry01");
        addTranslationDictionaryEntryRequest.setNote("this is a note");
        addTranslationDictionaryEntryRequest.setReference("this is a reference");
        client.getDictionaryApi().insertEntry(dictionaryId, addTranslationDictionaryEntryRequest);
    }

    @Test
    @Order(6)
    void exportEntryFile()  {
         client.getDictionaryApi().exportEntryFile(dictionaryId);
    }

    @Test
    @Order(7)
    void importEntryFile() {
        client.getDictionaryApi().importEntryFile(dictionaryId, new File("C:\\Users\\86195\\Desktop\\sdk\\词典.xlsx"));
    }

    @Test
    @Order(8)
    void deleteDictionaryEntry() {
        List entryNameList = new ArrayList<String>();
        entryNameList.add("entry01");
        client.getDictionaryApi().deleteDictionaryEntry(dictionaryId, entryNameList);
    }

    @Test
    @Order(9)
    void deleteDictionary() {
        List dictionaryIdList = new ArrayList<String>();
        dictionaryIdList.add(dictionaryId);
        client.getDictionaryApi().deleteDictionary(dictionaryIdList);
    }
}