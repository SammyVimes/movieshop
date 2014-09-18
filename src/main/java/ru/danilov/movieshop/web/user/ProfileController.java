package ru.danilov.movieshop.web.user;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.comment.Comment;
import ru.danilov.movieshop.core.entity.comment.CommentManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserSettings;
import ru.danilov.movieshop.core.util.ContentManager;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semyon on 10.09.2014.
 */
public class ProfileController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    private ContentManager contentManager = ServiceContainer.getService(ContentManager.class);

    private CommentManager commentManager = ServiceContainer.getService(CommentManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("showProfile")) {
            profileView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("uploadAvatar")) {
            uploadAvatar(request, response);
        }
    }

    private void profileView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/user.profile.tiles");
        String userIdString = request.getParameter("id");
        Long userId = Long.valueOf(userIdString);
        User user = userManager.getUserById(userId);
        if (user == null) {
            modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        User thisUser = null;
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            if (authData != null) {
                thisUser = authData.getUser();
                if (thisUser != null) {
                    UserSettings userSettings = userManager.getUserSettings(user);
                    if (userSettings == null) {
                        userSettings = new UserSettings();
                        userSettings.setMoney(4000.0);
                        userSettings.setUser(user);
                        userSettings.setMovies(new LinkedList<Movie>());
                        userSettings.setCart(new LinkedList<Movie>());
                        userManager.createSettings(userSettings);
                    }
                    if (thisUser != null && user.getId() == thisUser.getId()) {
                        modelAndView.putObject("money", userSettings.getMoney());
                    }
                }
            }
        }
        List<Comment> commentList = commentManager.getCommentsOfUser(user);
        modelAndView.putObject("comments", commentList);
        modelAndView.putObject("profile", user);
        modelAndView.process(request, response);
    }

    private void uploadAvatar(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        User thisUser = null;
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            if (authData != null) {
                thisUser = authData.getUser();
            }
        }
        if (thisUser == null) {
            show404(request, response);
        }
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (!isMultiPart) {
            LOGGER.error("Form type is not multipart/form-data");
            LOGGER.error("File Not Uploaded");
        } else {
            FileItemFactory dfit = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(dfit);
            List aList = null;

            try {
                aList = sfu.parseRequest(request);
            } catch (FileUploadException fue) {
                fue.printStackTrace();
            }

            Iterator itr = aList.iterator();
            while (itr.hasNext()) {
                FileItem fi = (FileItem) itr.next();
                LOGGER.trace("File Name: " + fi.getFieldName());
                LOGGER.trace("File Size: " + fi.getSize());

                try {
                    String fileName = generateUserAvatarFilename(thisUser);
                    File f = new File(contentManager.getImageBaseFolder() + "\\", fileName);
                    fi.write(f);
                    thisUser.setAvatarURL(fileName);
                    userManager.updateUser(thisUser);
                } catch (Exception e) {
                    LOGGER.error("Failed to upload file: " + e.getMessage());
                }
            }
        }
        response.sendRedirect("/movieshop/web/app/user/profile/showProfile?id=" + thisUser.getId());
    }

    private String generateUserAvatarFilename(final User user) {
        return "avatarUser" + user.getId() + ".png";
    }

}