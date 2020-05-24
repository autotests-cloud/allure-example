package qa.guru.allure;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubClient {

    @Headers({
            "Authorization: token 6ffaa85dd3896b386fe2cf3b3f0040e5c8f4985d"
    })
    @POST("/repos/{owner}/{repo}/issues")
    Call<Issue> createIssue(@Path("owner") String owner,
                            @Path("repo") String repo,
                            @Body Issue issue);


}
