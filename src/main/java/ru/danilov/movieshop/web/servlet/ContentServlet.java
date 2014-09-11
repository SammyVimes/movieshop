package ru.danilov.movieshop.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by semyon on 11.09.14.
 */
public class ContentServlet extends BaseServlet {

    private static final int BUFSIZE = 4096;

    private String contentFolder = "";

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        contentFolder = config.getInitParameter("folder");
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String filePath = contentFolder + "\\" + request.getParameter("filePath");
        Path path = Paths.get(filePath);
        File file = new File(filePath);
        int length   = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context  = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(filePath);

        if (mimetype == null) {
            mimetype = Files.probeContentType(path);
        }
        response.setContentType(mimetype);
        response.setContentLength((int) file.length());
        String fileName = (new File(filePath)).getName();

        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));

        while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
            outStream.write(byteBuffer,0,length);
        }

        in.close();
        outStream.close();
    }

}
