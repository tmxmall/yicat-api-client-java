package com.yicat.client;

import com.yicat.client.core.YicatApi;
import com.yicat.client.core.model.ClientConfig;
import com.yicat.client.core.model.Credentials;
import com.yicat.client.dictionary.DictionaryApi;
import com.yicat.client.document.DocumentApi;
import com.yicat.client.field.FieldApi;
import com.yicat.client.language.LanguageAPI;
import com.yicat.client.member.MemberAPI;
import com.yicat.client.project.ProjectApi;
import com.yicat.client.segment.SegmentApi;
import com.yicat.client.segment.StringsSegmentApi;
import com.yicat.client.task.TaskApi;
import com.yicat.client.termbase.TermBaseApi;
import com.yicat.client.translationmemory.TranslationMemoryApi;
import com.yicat.client.workflow.WorkflowsApi;
import lombok.Getter;

@Getter
public class Client extends YicatApi {

    private final TranslationMemoryApi translationMemoryApi;
    private final SegmentApi segmentApi;
    private final MemberAPI memberAPI;
    private final LanguageAPI languageAPI;
    private final ProjectApi projectApi;
    private final FieldApi fieldApi;
    private final WorkflowsApi workflowsApi;
    private final StringsSegmentApi stringsSegmentApi;
    private final TaskApi taskApi;
    private final TermBaseApi termBaseApi;
    private final DocumentApi documentApi;
    private final DictionaryApi dictionaryApi;

    public Client(Credentials credentials) {
        super(credentials);
        this.translationMemoryApi = new TranslationMemoryApi(credentials);
        this.segmentApi = new SegmentApi(credentials);
        this.memberAPI = new MemberAPI(credentials);
        this.languageAPI = new LanguageAPI(credentials);
        this.projectApi = new ProjectApi(credentials);
        this.fieldApi = new FieldApi(credentials);
        this.workflowsApi = new WorkflowsApi(credentials);
        this.stringsSegmentApi = new StringsSegmentApi(credentials);
        this.taskApi = new TaskApi(credentials);
        this.termBaseApi = new TermBaseApi(credentials);
        this.documentApi = new DocumentApi(credentials);
       this.dictionaryApi = new DictionaryApi(credentials);
    }

    public Client(Credentials credentials, ClientConfig clientConfig) {
        super(credentials, clientConfig);
        this.translationMemoryApi = new TranslationMemoryApi(credentials, clientConfig);
        this.segmentApi = new SegmentApi(credentials, clientConfig);
        this.memberAPI = new MemberAPI(credentials, clientConfig);
        this.languageAPI = new LanguageAPI(credentials, clientConfig);
        this.projectApi = new ProjectApi(credentials, clientConfig);
        this.fieldApi = new FieldApi(credentials, clientConfig);
        this.workflowsApi = new WorkflowsApi(credentials, clientConfig);
        this.stringsSegmentApi = new StringsSegmentApi(credentials, clientConfig);
        this.taskApi = new TaskApi(credentials, clientConfig);
        this.termBaseApi = new TermBaseApi(credentials, clientConfig);
        this.documentApi = new DocumentApi(credentials, clientConfig);
       this.dictionaryApi = new DictionaryApi(credentials, clientConfig);
    }

}
