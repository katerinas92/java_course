package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;
import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    // устанавливаем соединение с гитхабом через API; указываем сгенерированный в гитхабе токен пользователя
    Github github = new RtGithub("7f53ad1661689386a181e9a7a712ed77baf1bbfc");
    // получаем список коммитов из репозитория указанного юзера
    RepoCommits commits = github.repos().get(new Coordinates.Simple("katerinas92", "java_course")).commits();
    // выводим в цикле весь список полученных коммитов с комментариями
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
