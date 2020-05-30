package qa.guru.allure;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubClient {

    @POST("/repos/{owner}/{repo}/issues")
    Call<Issue> createIssue(@Path("owner") String owner,
                            @Path("repo") String repo,
                            @Body Issue issue);


}
