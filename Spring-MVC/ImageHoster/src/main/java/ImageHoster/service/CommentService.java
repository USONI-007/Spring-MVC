package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //Call the getAllCommentsByImageId() method in the Repository and obtain a List of all the comments in the database
    public List<Comment> getAllCommentsByImageId(Integer imageId) {
        return commentRepository.getAllCommentsByImageId(imageId);
    }


    //The method calls the updateComment() method in the Repository and passes the comment to be persisted in the database
    public List<Comment> updateComment(Comment comment,Integer imageId) {
        return commentRepository.updateComment(comment,imageId);
    }
}
