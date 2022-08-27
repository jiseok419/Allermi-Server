package com.example.Allermi.domain.Like.Controller;

import com.example.Allermi.domain.Like.Service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LikeApiController {
    private final LikeService likeService;

    @PostMapping("/like/{BoardId}")
    public ResponseEntity<String> addLike(Authentication authentication, @PathVariable Long BoardId) {
        String username = authentication.getName();
        boolean result = false;

        if (username != null) {
            result = likeService.addLike(username, BoardId);
        }

        return result ?
                new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
