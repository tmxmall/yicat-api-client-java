# API 使用说明文档

该文档提供了使用 YiCAT API 进行文件翻译的示例代码，并详细说明了每个部分的作用和参数。

## 准备工作

在使用该代码之前，需要完成以下准备工作：

1. 生成 API Token：在 YiCAT 服务中生成 API Token，该 Token 将用于身份验证。
2. 设置 `baseUrl`：将 `baseUrl` 设置为 YiCAT 服务的地址。

## 示例代码

```java
public static void main(String[] args) throws InterruptedException {
        // 请先生成token，并设置baseUrl，baseUrl为YiCAT服务的地址
        Credentials credentials = new Credentials("fc6a127932ac411fbe7534a11cfdf54c", "http://192.168.1.186");
        Client client = new Client(credentials);
        // 项目名称可能根据情况自己取名字
        String projectName = "project01";
        String srcLan = "zh-CN";
        // 快速翻译，只传一个目标语言
        List<String> tgtLanList = Arrays.asList("en-US");
        String mtProvider = "TranSmart";
        File file = new File("C:\\Users\\Desktop\\nature1-zh-少1.doc");
        // 获取所有的记忆库和术语库id，用于挂载，也可以不挂载。注意语言方向要匹配
        List<String> tmIds = client.getTranslationMemoryApi().listTMInfo().
        stream().map(ApiYiCATTMInfo::getTmId).collect(Collectors.toList());
        List<String> tbIds = client.getTermBaseApi().listTBInfo().
        stream().map(ApiYiCATTBInfo::getTbId).collect(Collectors.toList());
        //快速实现一个文件上传及翻译
        QuickTransSample.quickTrans(client, projectName, srcLan, tgtLanList, file, mtProvider, tmIds, tbIds);
        }
```

## 使用说明

以下是对上述代码中各部分的详细说明：

1. **生成 API Token**：在 YiCAT 服务中生成 API Token，并将其作为参数传递给 `Credentials` 类的构造函数。该 Token 用于身份验证，确保您有权访问 YiCAT 服务。

   ```java
   Credentials credentials = new Credentials("your_api_token", "http://your_yicat_base_url");
   ```

2. **创建客户端**：使用 `Credentials` 对象创建 `Client` 客户端实例，该实例将用于与 YiCAT API 进行通信。

   ```java
   Client client = new Client(credentials);
   ```

3. **设置项目名称和源语言**：根据您的项目需求，设置项目名称和源语言。

   ```java
   String projectName = "project01";
   String srcLan = "zh-CN";
   ```

4. **设置目标语言列表**：根据需要翻译的语言，将目标语言列表传递给 `tgtLanList` 参数。可以指定多个目标语言，用逗号分隔。
   
    ```java
    List<String> tgtLanList = Arrays.asList("en-US");
   ```

5. **设置机器翻译提供商**：根据您的选择，设置机器翻译提供商，将其作为参数传递给 `mtProvider`。
   
   ```java
   String mtProvider = "TranSmart";
   ```

6. **选择要翻译的文件**：将要翻译的文件路径传递给 `file` 参数。

   ```java
   File file = new File("path_to_your_file");
   ```

7. **获取记忆库和术语库 ID**：如果需要挂载记忆库和术语库，可以通过以下代码获取它们的 ID。确保语言方向匹配。

   ```java
   List<String> tmIds = client.getTranslationMemoryApi().listTMInfo()
           .stream().map(ApiYiCATTMInfo::getTmId).collect(Collectors.toList());

   List<String> tbIds = client.getTermBaseApi().listTBInfo()
           .stream().map(ApiYiCATTBInfo::getTbId).collect(Collectors.toList());
   ```

8. **文件上传和翻译**：通过调用 `QuickTransSample.quickTrans()` 方法实现文件上传和翻译功能。

   ```java
   QuickTransSample.quickTrans(client, projectName, srcLan, tgtLanList, file, mtProvider, tmIds, tbIds);
   ```

## 注意事项

- 在使用该示例代码之前，请确保已正确设置 API Token 和 Base URL。
- 根据实际需要，可以根据示例代码自行调整项目名称、源语言、目标语言和文件路径等参数。
- 如果需要挂载记忆库和术语库，请确保其语言方向与翻译需求相匹配。
- 本工程中还有其他示例代码，您可以根据需要进行调整和使用。具体接口参数方法可以参考YiCAT Open API文档 https://www.yicat.vip/api/v1/doc/。