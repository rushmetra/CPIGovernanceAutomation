package org.project.backend.repository.github;

import lombok.RequiredArgsConstructor;
import org.project.backend.repository.github.dto.GithubRepositoryEditRequest;
import org.project.backend.repository.github.dto.GithubRepositoryRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/repositories/github")
@RequiredArgsConstructor
public class GithubRepositoryController {

    public final GithubRepositoryService githubRepositoryService;

    @PostMapping("/save")
    public ResponseEntity<GithubRepository> saveRepository(@RequestBody GithubRepositoryRegisterRequest githubRepositoryRegisterRequest) {
        var repository =  githubRepositoryService.save(githubRepositoryRegisterRequest);
        return ResponseEntity.ok(repository);
    }


    @PostMapping("/update")
    public ResponseEntity<GithubRepository> editRepository(@RequestBody GithubRepositoryEditRequest githubRepositoryEditRequest) {
        var repository =  githubRepositoryService.update(githubRepositoryEditRequest);
        return ResponseEntity.ok(repository);
    }


    @GetMapping("/key")
    public ResponseEntity<GithubRepository> listRepository() {
        var repository =  githubRepositoryService.findByCredentials();
        return ResponseEntity.ok(repository);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRepository(@RequestParam Integer id) {
        githubRepositoryService.delete(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/branches")
    public ResponseEntity<List<String>> getBranches() {
        List<String> branches = githubRepositoryService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
}
