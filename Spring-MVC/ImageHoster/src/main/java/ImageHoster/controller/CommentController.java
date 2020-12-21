package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments",method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId")Integer imageId,@RequestParam("comment")String text , Comment comment,HttpSession session,Model model )throws IOException {
        Image image = imageService.getImage(imageId);

        User user=(User)session.getAttribute("loggeduser");
        comment.setUser(user);
        comment.setText(text);
        comment.setImage(image);
        comment.setCreatedDate(new Date());
        commentService.updateComment(comment,imageId);
        List<Comment> comments=commentService.getAllCommentsByImageId(imageId);

        model.addAttribute("comments",comments);
        model.addAttribute("image", image);

        return "images/image";
    }
}
