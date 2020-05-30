package qa.guru.allure;

import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSteps {

    private static final String TOKEN = "6ffaa85dd3896b386fe2cf3b3f0040e5c8f4985d";
    private GithubClient github;

    public ApiSteps() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor((chain) -> {
                    final Request request = chain.request().newBuilder()
                            .addHeader("Authorization", String.format("token %s", TOKEN)).build();
                    return chain.proceed(request);
                })
                .addInterceptor(new AllureOkHttp3())
                .build();

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(client)
                .build();
        github = retrofit.create(GithubClient.class);
    }

    @Step("Create issue by api")
    public Issue createIssue(final String owner,
                             final String repo,
                             final String title,
                             final String body) throws Exception {
        final Issue issue = new Issue();
        issue.setTitle(title);
        issue.setBody(body);
        return github.createIssue(owner, repo, issue).execute().body();
    }

}
