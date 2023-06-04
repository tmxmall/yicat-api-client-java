package vip.yicat.client;

import vip.yicat.client.core.YiCATApi;
import vip.yicat.client.core.model.ClientConfig;
import vip.yicat.client.core.model.Credentials;
import vip.yicat.client.dictionary.DictionaryApi;
import vip.yicat.client.document.DocumentApi;
import vip.yicat.client.field.FieldApi;
import vip.yicat.client.language.LanguageAPI;
import vip.yicat.client.member.MemberAPI;
import vip.yicat.client.project.ProjectApi;
import vip.yicat.client.segment.SegmentApi;
import vip.yicat.client.segment.StringsSegmentApi;
import vip.yicat.client.task.TaskApi;
import vip.yicat.client.termbase.TermBaseApi;
import vip.yicat.client.translationmemory.TranslationMemoryApi;
import vip.yicat.client.workflow.WorkflowsApi;
import lombok.Getter;

@Getter
public class Client extends YiCATApi {

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
