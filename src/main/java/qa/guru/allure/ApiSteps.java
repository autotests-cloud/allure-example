package qa.guru.allure;

import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSteps {

    private GithubClient github;

    public ApiSteps() {
        final OkHttpClient client = new OkHttpClient.Builder()
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
