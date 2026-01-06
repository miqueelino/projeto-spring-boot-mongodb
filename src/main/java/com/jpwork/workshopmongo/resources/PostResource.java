package com.jpwork.workshopmongo.resources;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpwork.workshopmongo.domain.Post;
import com.jpwork.workshopmongo.resources.util.URL;
import com.jpwork.workshopmongo.services.PostService;


	
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

	@GetMapping("/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {

		text = URLDecoder.decode(text, StandardCharsets.UTF_8);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
    
    
    
    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch (
    	@RequestParam(value = "text", defaultValue = "") String text,
    	@RequestParam(value = "minDate", defaultValue = "") String minDate,
    	@RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        text = URLDecoder.decode(text, StandardCharsets.UTF_8);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date(0L));
        List<Post> list = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(list);
    }
}
