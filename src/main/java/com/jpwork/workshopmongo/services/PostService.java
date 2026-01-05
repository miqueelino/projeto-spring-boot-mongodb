package com.jpwork.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpwork.workshopmongo.domain.Post;
import com.jpwork.workshopmongo.repository.PostRepository;
import com.jpwork.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	


	    private final PostRepository repo;

	    @Autowired
	    public PostService(PostRepository repo) {
	        this.repo = repo;
	    }

	    public Post findById(String id) {
	        if (id == null || id.isBlank()) {
	            throw new IllegalArgumentException("ID do post não pode ser nulo ou vazio");
	        }

	        return repo.findById(id)
	                .orElseThrow(() ->
	                        new ObjectNotFoundException("Post não encontrado. Id: " + id)
	                );
	    }
	    

	    public List<Post> findByTitle (String text){
	    	return repo.findByTitleContainingIgnoreCase(text);
	    }


	}



